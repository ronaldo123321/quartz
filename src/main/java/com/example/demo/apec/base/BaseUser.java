package com.example.demo.apec.base;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * @author ronaldo
 * @version 2.0
 * @date 2019/6/19 15:16
 */
public class BaseUser {


    public static void main(String[] args) throws SchedulerException {

        //创建一个Scheduler作为调度器对象
        SchedulerFactory  schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        //获取下一分钟的调度时间
        Date runTime = new Date();

        //每一个job都归功于一个name和一个group作为任务的坐标，创建一个JobDetail




    }
}
