package com.example.demo.apec.service;

import com.example.demo.apec.Job.DynamicJob;
import com.example.demo.apec.dao.JobEntityRepository;
import com.example.demo.apec.entity.JobEntity;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bogao
 * @version 2.0
 * @date 2019/6/18 19:36
 */
@Service
public class DynamicJobService {

    @Autowired
    private JobEntityRepository jobEntityRepository;


    public JobEntity getJobEntityById(Integer id){
        return jobEntityRepository.getById(id);
    }


    public List<JobEntity>  loadJobs(){
        List list = new ArrayList();
        jobEntityRepository.findAll().forEach(list::add);
        return list;
    }

    //获取JobDataMap（job参数对象）
    public JobDataMap getJobDataMap(JobEntity job){
        JobDataMap map = new JobDataMap();
        map.put("name",job.getName());
        map.put("jobGroup", job.getJobGroup());
        map.put("cronExpression", job.getCron());
        map.put("parameter", job.getParameter());
        map.put("jobDescription", job.getDescription());
        map.put("vmParam", job.getVmParam());
        map.put("jarPath", job.getJarPath());
        map.put("status", job.getStatus());
        return map;
    }


    //获取JobDetail,JobDetail是任务的定义，而job是任务的执行逻辑，jobDetail里会引用一个Job Class来定义
    public JobDetail getJobDetail(JobKey jobKey,String description, JobDataMap map){
        return JobBuilder.newJob(DynamicJob.class)
                .withIdentity(jobKey)
                .withDescription(description)
                .setJobData(map)
                .storeDurably()
                .build();
    }

    //获取Trigger（Job的触发器 执行规则）
    public Trigger getTrigger(JobEntity job){
        return TriggerBuilder.newTrigger()
                .withIdentity(job.getName(),job.getJobGroup())
                .withSchedule(CronScheduleBuilder.cronSchedule(job.getCron()))
                .build();
    }


    //获取Jobkey 包含Name和Group
    public JobKey getJobKey(JobEntity job){
        return  JobKey.jobKey(job.getName(),job.getJobGroup());
    }




}
