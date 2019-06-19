package com.example.demo.apec.Job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ronaldo
 * @version 2.0
 * @date 2019/6/19 15:13
 */
@Slf4j
public class BaseJob implements Job {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        log.info("当前执行任务时间："+simpleDateFormat.format(new Date()));
        log.info(jobExecutionContext.getTrigger().getKey().getGroup());
        log.info(jobExecutionContext.getTrigger().getKey().getName());

    }
}
