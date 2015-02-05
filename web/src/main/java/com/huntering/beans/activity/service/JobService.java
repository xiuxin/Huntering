package com.huntering.beans.activity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huntering.beans.activity.entity.Job;
import com.huntering.beans.activity.repository.JobRepository;
import com.huntering.common.service.BaseService;

/**
 * 
 * @author Bell Qiu
 *
 */
@Service
public class JobService extends BaseService<Job, Long> {

    @Autowired
    private JobRepository getJobRepository() {
        return (JobRepository) baseRepository;
    }

}
