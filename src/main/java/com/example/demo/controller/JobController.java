package com.example.demo.controller;

import com.example.demo.apec.dao.JobEntityRepository;
import com.example.demo.apec.entity.JobEntity;
import com.example.demo.apec.service.DynamicJobService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.Set;

/**
 * @author bogao
 * @version 2.0
 * @date 2019/6/19 10:40
 */
@RestController
@Slf4j
public class JobController {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;
    
    @Autowired
    private DynamicJobService jobService;
    
    @Autowired
    private JobEntityRepository repository;
    
    
    //初始化并重启所有的job
    @PostConstruct
    public void initialize(){
        try {
            reStartAllJobs();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        log.info("INIT SUCCESS");
    }



    @PostMapping("/refresh/{id}")
    public String refresh(@PathVariable @NonNull Integer id) throws SchedulerException {
        String result;
        JobEntity entity = jobService.getJobEntityById(id);
        if (Objects.isNull(entity)){
            return "error: id is not exist ";
        }
        synchronized (log) {
            JobKey jobKey = jobService.getJobKey(entity);
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            scheduler.pauseJob(jobKey);
            scheduler.unscheduleJob(TriggerKey.triggerKey(jobKey.getName(), jobKey.getGroup()));
            scheduler.deleteJob(jobKey);
            JobDataMap map = jobService.getJobDataMap(entity);
            JobDetail jobDetail = jobService.getJobDetail(jobKey, entity.getDescription(), map);
            if (entity.getStatus().equals("OPEN")) {
                scheduler.scheduleJob(jobDetail, jobService.getTrigger(entity));
                result = "Refresh Job : " + entity.getName() + "\t jarPath: " + entity.getJarPath() + " success !";
            } else {
                result = "Refresh Job : " + entity.getName() + "\t jarPath: " + entity.getJarPath() + " failed ! , " +
                        "Because the Job status is " + entity.getStatus();
            }
        }
        return result;
    }

    //重启数据库中的所有的Job
    @PostMapping("/refresh/all")
    public String refreshAll(){
        String result;

        try {
            reStartAllJobs();
            result = "SUCCESS";
        } catch (SchedulerException e) {
            result = "Exception:"+e.getMessage();
        }
        return "refresh all jobs:"+result;


    }


    /**
     * 重新启动所有的job
     */
    private void reStartAllJobs() throws SchedulerException {

        synchronized (log){
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            Set<JobKey> jobKeys = scheduler.getJobKeys(GroupMatcher.anyGroup());
            scheduler.pauseJobs(GroupMatcher.anyGroup());//暂停所有的Job
            //删除数据库中所有注册的job 然后重启拉起
            for (JobKey jobKey: jobKeys) {
                scheduler.unscheduleJob(TriggerKey.triggerKey(jobKey.getName(),jobKey.getGroup()));
                scheduler.deleteJob(jobKey);
            }
            for (JobEntity job: jobService.loadJobs()) {
                log.info("Job register name:{},group:{},cron:{}",job.getName(),job.getJobGroup(),job.getCron());
                JobDataMap map = jobService.getJobDataMap(job);
                JobKey jobKey = jobService.getJobKey(job);
                JobDetail jobDetail = jobService.getJobDetail(jobKey, job.getDescription(), map);
                if(job.getStatus().equals("OPEN")){
                    scheduler.scheduleJob(jobDetail,jobService.getTrigger(job));
                } else {
                    log.info("Job jump name : {} , Because {} status is {}", job.getName(), job.getName(), job.getStatus());
                }
            }


        }

    }


}
