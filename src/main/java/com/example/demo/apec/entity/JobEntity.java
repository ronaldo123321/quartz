package com.example.demo.apec.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author bogao
 * @version 2.0
 * @date 2019/6/18 19:09
 */
@Entity
@Table(name = "JOB_ENTITY")
@Data
@Accessors(chain = true)
public class JobEntity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 任务名
     */
    private String name;

    /**
     * job组名
     */
    private String jobGroup;

    /**
     * 执行的cron
     */
    private String cron;

    /**
     * job参数
     */
    private String parameter;

    /**
     * job描述信息
     */
    private String description;

    /**
     * vm参数
     */
    private String vmParam;

    /**
     * job的jar路径
     */
    private String jarPath;

    /**
     * job的执行状态这里我设置为OPEN/CLOSE且只有该值为OPEN才会执行该Job
     */
    private String status;




}
