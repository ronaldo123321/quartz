package com.example.demo.apec.service;

import com.example.demo.apec.dao.JobEntityRepository;
import com.example.demo.apec.entity.JobEntity;
import org.quartz.JobDataMap;
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

    //获取JobDataMap
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



}
