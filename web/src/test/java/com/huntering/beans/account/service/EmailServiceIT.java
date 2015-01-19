package com.huntering.beans.account.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.huntering.beans.account.entity.Account;
import com.huntering.beans.account.entity.Email;
import com.huntering.beans.account.exception.AccountException;

/**
 * 
 * @author Vincent Yao
 *
 */
public class EmailServiceIT extends BaseAccountIT {

    
    /**************** Email TEST *****************/
	@Test(expected = AccountException.class)
	public void testVerifyEmail_NullEmailId() {
        Account account = this.createInactiveAccount();
        Email email = account.getEmails().get(0);
        String encryptCode = passwordService.encryptPassword(email.getEmail(), account.getSalt());
        emailService.verifyEmail(null, encryptCode);
	}

	@Test(expected = AccountException.class)
	public void testVerifyEmail_EmptyEmailCode() {
        Account account = this.createInactiveAccount();
        Email email = account.getEmails().get(0);
        String encryptCode = passwordService.encryptPassword(email.getEmail(), account.getSalt());
        emailService.verifyEmail(email.getId(), " ");
	}
	
	@Test(expected = AccountException.class)
	public void testVerifyEmail_EmptyAlreadyActive() {
        Account account = createDefaultUserWithEmail();
        Email email = account.getEmails().get(0);
        String encryptCode = passwordService.encryptPassword(email.getEmail(), account.getSalt());
        emailService.verifyEmail(email.getId(), encryptCode);
	}

	@Test
	public void testVerifyEmail_EmptyInvalidValidationCode() {
        Account account = createInactiveAccount();
        Email email = account.getEmails().get(0);
        String encryptCode = passwordService.encryptPassword(email.getEmail(), account.getSalt());
        emailService.verifyEmail(email.getId(), "WTF");
        assertEquals("sd ", "sdddd");
	}
	
    @Test
    public void testAddNewEmail() {
        Account account = createDefaultUserWithEmail();
        account.addEmail(new Email(account, "fuck@126.com", false, false));
        accountService.saveAndFlush(account);
        
        account = accountService.findByEmail(email);
        assertEquals(2, account.getEmails().size());
    }

    @Test
    public void testGetPrimaryEmail() {
        Account account = createDefaultUserWithEmail();
        account.addEmail(new Email(account, "fuck@126.com", false, false));
        accountService.saveAndFlush(account);
        
        Email emailAddr = emailService.findPrimaryEmailByAccount(account);
        assertEquals(emailAddr.getEmail(), email);
    }
    
    @Test
    public void testGetEmailByAccount() {
        Account account = createDefaultUserWithEmail();
        account.addEmail(new Email(account, "fuck@126.com", false, false));
        accountService.saveAndFlush(account);
        
        List<Email> emails = emailService.findEmailsByAccount(account);
        assertEquals(emails.size(), 2);
        
        emails = emailService.findEmailsByAccountId(account.getId());
        assertEquals(emails.size(), 2);
    }
}
