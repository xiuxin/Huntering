package com.huntering.beans.profile.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huntering.beans.account.entity.Account;
import com.huntering.beans.account.service.AccountService;
import com.huntering.beans.profile.entity.People;
import com.huntering.beans.profile.entity.PeopleCompany;
import com.huntering.beans.profile.entity.PeopleEducation;
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

    @Autowired
    private AccountService accountService;
    
    public List<People> findByAccountId(Long accountId) {
    	return getPeopleRepository().findByAccountId(accountId);
    }

    public List<People> findPeopleByAccountIdAndSelf(Long accountId, boolean self) {
    	return getPeopleRepository().findByAccountIdAndSelf(accountId, self);
    }

    public List<People> findSelfPeopleByAccountIdAndSelf(Long accountId) {
    	return getPeopleRepository().findByAccountIdAndSelf(accountId, true);
    }
    
    public People createPeople(Long id, People people) {
    	Account account = accountService.getAccountById(id);
    	
    	if (account == null) {
    		throw new RuntimeException("Account not exists");
    	}
    	
    	people.setAccount(account);
    	return save(people);
//    	account.addPeople(people);
//    	accountService.update(account);
//    	return people;
    }
    
    public People getPeopleProfile(Long peopleId) {
    	
    	People people = findOne(peopleId);
    	people.getAccount().setPeople(Collections.<People> emptyList());
    	
    	for (PeopleEducation pEducation : people.getPeopleEducation()) {
    		pEducation.setPeople(null);
    	}

    	for (PeopleCompany pCompany : people.getPeopleCompany()) {
    		pCompany.getCompany().setPeopleCompany(Collections.<PeopleCompany> emptyList());
    		pCompany.setPeople(null);
    	}
    	
    	return people;
    }
    
}
