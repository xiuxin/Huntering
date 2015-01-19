package com.huntering.beans.account.service;

import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import com.huntering.beans.account.entity.Account;
import com.huntering.beans.account.entity.Email;
import com.huntering.beans.account.entity.InvitationCode;
import com.huntering.test.BaseIT;

/**
 * 
 * @author Vincent Yao
 *
 */
public abstract class BaseAccountIT extends BaseIT {


    @Autowired
    protected AccountService accountService;

    @Autowired
    protected AccountPasswordService passwordService;

    @Autowired
    protected InvitationCodeService invCodeService;
    
    @Autowired
    protected EmailService emailService;

    protected int maxtRetryCount = 10;

    protected String email = "vincent.yao@sap.com";
    protected String password = "12345";
    protected String inviationCode = "inviCode1";

    @Before
    public void setUp() {
        accountService.setPasswordService(passwordService);
        passwordService.setMaxRetryCount(maxtRetryCount);

        Account user = accountService.findByEmail(email);
        if (user != null) {
            accountService.delete(user);//因为用户是逻辑删除 此处的目的主要是清 缓存
            delete(user);              //所以还需要物理删除
        }
        
        InvitationCode invCode = invCodeService.findByCode(inviationCode);
        if (invCode != null) {
        	invCodeService.delete(invCode);
        }
        
        clear();
    }

    @After
    public void tearDown() {
        passwordService.clearLoginRecordCache(email);
    }


    protected Account createUser(String email, String password, boolean active) {
        Account account = new Account();
        account.setPassword(password);
        account.setActive(active);
        accountService.saveAndFlush(account);
        return account;
    }
    
    protected Account createDefaultUser(boolean active) {
        return createUser(email, password, true);
    }
    
    protected Account createInactiveAccount() {
        Account account = createDefaultUser(false);
        account.addEmail(new Email(account, email, true, false));
        accountService.update(account);
        return account;
    }
    
    protected Account createDefaultUserWithEmail() {
        Account account = createDefaultUser(true);
        account.addEmail(new Email(account, email, true, true));
        accountService.update(account);
        return account;
    }

    protected InvitationCode createDefaultInvitationCode(Boolean used) {
    	InvitationCode code = new InvitationCode();
    	code.setUsed(used);
    	code.setCode(inviationCode);
    	invCodeService.saveAndFlush(code);
    	return code;
    }
    
}
