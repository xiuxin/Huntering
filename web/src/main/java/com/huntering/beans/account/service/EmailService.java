package com.huntering.beans.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.huntering.beans.account.entity.Email;
import com.huntering.beans.account.repository.EmailRepository;
import com.huntering.common.service.BaseService;

/**
 * 
 * @author Vincent Yao
 *
 */
@Service
public class EmailService extends BaseService<Email, Long> {

    @Autowired
    private EmailRepository getEmailRepository() {
        return (EmailRepository) baseRepository;
    }
    
    @Autowired
    private AccountPasswordService passwordService;

    public void setPasswordService(AccountPasswordService passwordService) {
        this.passwordService = passwordService;
    }

    public Email findByEmail(String email) {
        if(StringUtils.isEmpty(email)) {
            return null;
        }
        return getEmailRepository().findByEmail(email);
    }

    public boolean isEmailUsed(String email) {
        return findByEmail(email) != null ? true : false;
    }
}
