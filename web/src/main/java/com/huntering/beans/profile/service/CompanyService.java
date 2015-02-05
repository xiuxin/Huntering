package com.huntering.beans.profile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huntering.beans.profile.entity.Company;
import com.huntering.beans.profile.repository.CompanyRepository;
import com.huntering.common.service.BaseService;

/**
 * 
 * @author Vincent Yao
 *
 */
@Service
public class CompanyService extends BaseService<Company, Long> {

    @Autowired
    private CompanyRepository getCompanyRepository() {
        return (CompanyRepository) baseRepository;
    }
    
}
