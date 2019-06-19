package com.example.demo.apec.dao;

import com.example.demo.apec.entity.JobEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ronaldo
 * @version 2.0
 * @date 2019/6/18 19:30
 */
@Repository
public interface JobEntityRepository extends CrudRepository<JobEntity, Long> {

    JobEntity getById(Integer id);
}
