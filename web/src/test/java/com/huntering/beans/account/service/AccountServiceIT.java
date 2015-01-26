package com.huntering.beans.account.service;

import org.junit.Assert;
import org.junit.Test;

import com.huntering.beans.account.entity.Account;
import com.huntering.beans.account.exception.AccountNotExistsException;
import com.huntering.beans.account.exception.AccountPasswordNotMatchException;
import com.huntering.beans.account.exception.AccountPasswordRetryLimitExceedException;
import com.huntering.beans.account.exception.DuplicatedEmailRegisterException;
import com.huntering.beans.account.exception.InvalidRegistrationInfoException;
import com.huntering.beans.account.exception.InvitationCodeException;

/**
 * 
 * @author Vincent Yao
 *
 */
public class AccountServiceIT extends BaseAccountIT {

    
    /**************** LOGIN TEST *****************/
    @Test
    public void testLoginSuccessWithEmail() {
        Account account = createDefaultUserWithEmail();
        Assert.assertNotNull(accountService.login(email, password));
    }

    @Test(expected = AccountNotExistsException.class)
    public void testLoginFailWithUserNotExists() {
        Account account = createDefaultUserWithEmail();
        accountService.login(email + "1", password);
    }

    @Test(expected = AccountNotExistsException.class)
    public void testLoginFailWithUserDeleted() {
        Account account = createDefaultUserWithEmail();
        accountService.delete(account);
        clear();
        accountService.login(email, password);
    }

    @Test(expected = AccountPasswordNotMatchException.class)
    public void testLoginFailWithUserPasswordNotMatch() {
        Account account = createDefaultUserWithEmail();
        accountService.login(email, password + "1");
    }

    @Test(expected = AccountPasswordRetryLimitExceedException.class)
    public void testLoginFailWithRetryLimitExceed() {
        Account account = createDefaultUserWithEmail();
        for (int i = 0; i < maxtRetryCount; i++) {
            try {
                accountService.login(email, password + "1");
            } catch (AccountPasswordNotMatchException e) {
            }
        }
        accountService.login(email, password + "1");
        passwordService.clearLoginRecordCache(email);
    }

    

    /**************** Registration TEST *****************/
    @Test
    public void testRegisterSuccess() {
    	createDefaultInvitationCode(false);
    	
        accountService.register(email, password, "TName", inviationCode);
//        accountService.login(email, password);
        passwordService.clearLoginRecordCache(email);
    }
    
    @Test(expected = InvalidRegistrationInfoException.class)
    public void testRegisterFailWithEmptyEmail() {
    	createDefaultInvitationCode(false);
        accountService.register("", password,"TName", inviationCode);
    }

    @Test(expected = InvalidRegistrationInfoException.class)
    public void testRegisterFailWithEmptyPassword() {
    	createDefaultInvitationCode(false);
        accountService.register(email, "", "TName", inviationCode);
    }

    @Test(expected = DuplicatedEmailRegisterException.class)
    public void testRegisterFailWithDuplicateEmail() {
    	createDefaultInvitationCode(false);
        accountService.register(email, password, "TName", inviationCode);
        accountService.register(email, password, "TName", inviationCode);
    }

    @Test(expected = InvalidRegistrationInfoException.class)
    public void testRegisterFail_EmptyInvitationCode() {
    	createDefaultInvitationCode(false);
        accountService.register(email, password, "TName", null);
    }

    @Test(expected = InvitationCodeException.class)
    public void testRegisterFail_InvitationCodeNotExists() {
    	createDefaultInvitationCode(false);
        accountService.register(email, password, "TName", "NotExistsCode");
    }

    @Test(expected = InvitationCodeException.class)
    public void testRegisterFail_UsedInvitaionCode() {
    	createDefaultInvitationCode(true);
        accountService.register(email, password, "TName", inviationCode);
    }
}
