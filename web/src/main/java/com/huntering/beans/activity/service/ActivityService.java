package com.huntering.beans.activity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huntering.beans.activity.entity.Activity;
import com.huntering.beans.activity.repository.ActivityRepository;
import com.huntering.common.service.BaseService;

/**
 * 
 * @author Bell Qiu
 *
 */
@Service
public class ActivityService extends BaseService<Activity, Long> {

    @Autowired
    private ActivityRepository getActivityRepository() {
        return (ActivityRepository) baseRepository;
    }

    public List<Activity> findByAccountId(Long accountId) {
    	return getActivityRepository().findByAccountId(accountId);
    }

}
