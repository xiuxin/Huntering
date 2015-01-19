package com.huntering.beans.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.huntering.beans.account.entity.Account;
import com.huntering.beans.account.entity.Email;
import com.huntering.beans.account.exception.AccountException;
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

    @Autowired
    private AccountService accountService;
    
    public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public void setPasswordService(AccountPasswordService passwordService) {
        this.passwordService = passwordService;
    }

    /**
     * Check the MD5 code to make the email active.
     * 
     * @param emailId
     * @param code
     * @return
     */
    public Email verifyEmail(Long emailId, String code) {
    	if (emailId == null || StringUtils.isEmpty(code)) {
    		throw new AccountException("email.error.activate", null);
    	}

    	Email email = findOne(emailId);
    	if (email == null) {
    		throw new AccountException("email.error.nonexist", null);
    	} 
    	
    	if (email.getActive()) {
    		throw new AccountException("email.error.alreayactive", null);
    	}
    	
    	Account account = email.getAccount();
    	String encrypCode = passwordService.encryptPassword(email.getEmail(), account.getSalt());
    	
    	if ( !encrypCode.equals(code)) {
    		throw new AccountException("email.error.activate", null);
    	}
    	
    	email.setActive(true);
    	if (!account.getActive()) {
    		account.setActive(true);    	
    		accountService.update(account);
    	} else {
    		update(email);
    	}
    	
    	return email;
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
    
    public Email findPrimaryEmailByAccount(Account account) {
    	return getEmailRepository().findPrimaryEmailByAccount(account);
    }
    
    public List<Email> findEmailsByAccountId(Long id) {
    	return getEmailRepository().findByAccountId(id);
    }
    
    public List<Email> findEmailsByAccount(Account account) {
    	return getEmailRepository().findByAccount(account);
    }
}
