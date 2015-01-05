package com.huntering.beans.account.service;

import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import com.huntering.beans.account.entity.Account;
import com.huntering.beans.account.entity.Email;
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
    protected EmailService emailService;

    protected int maxtRetryCount = 10;

    protected String email = "vincent.yao@sap.com";
    protected String password = "12345";

    @Before
    public void setUp() {
        accountService.setPasswordService(passwordService);
        passwordService.setMaxRetryCount(maxtRetryCount);

        Account user = accountService.findByEmail(email);
        if (user != null) {
            accountService.delete(user);//因为用户是逻辑删除 此处的目的主要是清 缓存
            delete(user);              //所以还需要物理删除
        }
        clear();
    }

    @After
    public void tearDown() {
        passwordService.clearLoginRecordCache(email);
    }


    protected Account createUser(String email, String password) {
        Account account = new Account();
        account.setPassword(password);
        accountService.saveAndFlush(account);
        return account;
    }
    
    protected Account createDefaultUser() {
        return createUser(email, password);
    }
    
    protected Account createDefaultUserWithEmail() {
        Account account = createDefaultUser();
        account.addEmail(new Email(account, email, true));
        accountService.update(account);
        return account;
    }

}
