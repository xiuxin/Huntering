package com.huntering.beans.profile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huntering.beans.profile.entity.PeopleCompany;
import com.huntering.beans.profile.repository.PeopleCompanyRepository;
import com.huntering.common.service.BaseService;

/**
 * 
 * @author Vincent Yao
 *
 */
@Service
public class PeopleCompanyService extends BaseService<PeopleCompany, Long> {

    @Autowired
    private PeopleCompanyRepository getPeopleCompanyRepository() {
        return (PeopleCompanyRepository) baseRepository;
    }
    
}
