package com.huntering.beans.profile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huntering.beans.profile.entity.People;
import com.huntering.beans.profile.repository.PeopleRepository;
import com.huntering.common.service.BaseService;

/**
 * 
 * @author Vincent Yao
 *
 */
@Service
public class PeopleService extends BaseService<People, Long> {

    @Autowired
    private PeopleRepository getPeopleRepository() {
        return (PeopleRepository) baseRepository;
    }

    public List<People> findByAccountId(Long accountId) {
    	return getPeopleRepository().findByAccountId(accountId);
    }

    public List<People> findPeopleByAccountIdAndSelf(Long accountId, boolean self) {
    	return getPeopleRepository().findByAccountIdAndSelf(accountId, self);
    }

    public List<People> findSelfPeopleByAccountIdAndSelf(Long accountId) {
    	return getPeopleRepository().findByAccountIdAndSelf(accountId, true);
    }
}
