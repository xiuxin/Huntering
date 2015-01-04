package com.huntering.beans.account.service;

import org.junit.Assert;
import org.junit.Test;

import com.huntering.beans.account.entity.Account;
import com.huntering.beans.account.exception.AccountNotExistsException;
import com.huntering.beans.account.exception.AccountPasswordNotMatchException;
import com.huntering.beans.account.exception.AccountPasswordRetryLimitExceedException;
import com.huntering.sys.user.exception.UserPasswordNotMatchException;

/**
 * 
 * @author Vincent Yao
 *
 */
public class AccountServiceIT extends BaseAccountIT {

    @Test
    public void testLoginSuccessWithEmail() {
        Account account = createUser(email, password);
        Assert.assertNotNull(accountService.login(account.getEmail(), password));
    }

    @Test(expected = AccountNotExistsException.class)
    public void testLoginFailWithUserNotExists() {
        Account account = createUser(email, password);
        accountService.login(email + "1", password);
    }

    @Test(expected = AccountNotExistsException.class)
    public void testLoginFailWithUserDeleted() {
        Account account = createUser(email, password);
        accountService.delete(account);
        clear();
        accountService.login(email, password);
    }

    @Test(expected = AccountPasswordNotMatchException.class)
    public void testLoginFailWithUserPasswordNotMatch() {
        Account account = createUser(email, password);
        accountService.login(email, password + "1");
    }

    @Test(expected = AccountPasswordRetryLimitExceedException.class)
    public void testLoginFailWithRetryLimitExceed() {
        Account account = createUser(email, password);
        for (int i = 0; i < maxtRetryCount; i++) {
            try {
                accountService.login(email, password + "1");
            } catch (AccountPasswordNotMatchException e) {
            }
        }
        accountService.login(email, password + "1");
        passwordService.clearLoginRecordCache(email);
    }
}
