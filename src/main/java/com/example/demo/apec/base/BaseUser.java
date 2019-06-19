package com.example.demo.apec.base;

import com.example.demo.apec.Job.BaseJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author ronaldo
 * @decription 测试定时调度任务,需改动部分配置文件
 * @date 2019/6/19 15:16
 *
 */
public class BaseUser {


    public static void main(String[] args) throws SchedulerException, InterruptedException {

        //创建一个Scheduler作为调度器对象
        SchedulerFactory  schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        //获取下一分钟的调度时间
        Date runTime = new Date();

        //每一个job都归功于一个name和一个group作为任务的坐标，创建一个JobDetail
        JobDetail jobDetail = newJob(BaseJob.class).withIdentity("job1", "group1").build();

        //每一个trigger都归功于一个Name和一个group作为触发器的坐标，创建触发器Trigger
        Trigger trigger = newTrigger().withIdentity("trigger1","group1").startAt(runTime).build();

        //调度器按JobDetail Trigger初始化调度器，相当于加入调度队列
        scheduler.scheduleJob(jobDetail,trigger);

        //开启调度
        scheduler.start();

        TimeUnit.MINUTES.sleep(1);

        //关闭调度
        scheduler.shutdown();
    }
}
