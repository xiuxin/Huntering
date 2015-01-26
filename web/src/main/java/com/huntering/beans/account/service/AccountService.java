package com.huntering.beans.account.service;

import java.util.Arrays;
import java.util.Date;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.huntering.beans.account.entity.Account;
import com.huntering.beans.account.entity.Email;
import com.huntering.beans.account.entity.InvitationCode;
import com.huntering.beans.account.exception.AccountException;
import com.huntering.beans.account.exception.AccountNotExistsException;
import com.huntering.beans.account.exception.AccountPasswordNotMatchException;
import com.huntering.beans.account.exception.DuplicatedEmailRegisterException;
import com.huntering.beans.account.exception.InvalidRegistrationInfoException;
import com.huntering.beans.account.exception.InvitationCodeException;
import com.huntering.beans.account.repository.AccountRepository;
import com.huntering.beans.profile.entity.People;
import com.huntering.beans.profile.service.PeopleService;
import com.huntering.common.service.BaseService;
import com.huntering.sys.user.entity.User;
import com.huntering.sys.user.utils.UserLogUtils;

/**
 * 
 * @author Vincent Yao
 *
 */
@Service
public class AccountService extends BaseService<Account, Long> {

    @Autowired
    private AccountRepository getAccountRepository() {
        return (AccountRepository) baseRepository;
    }

	@Autowired
	private MailSender mailSender;
    @Autowired
	private SimpleMailMessage message;
	
    @Autowired
    private AccountPasswordService passwordService;
    
    @Autowired
    private PeopleService peopleService;

	@Autowired
    private InvitationCodeService invitationCodeService;
    
    @Autowired
    private EmailService emailService;
	
    @Override
    public Account save(Account account) {
        if (account.getCreateDate() == null) {
            account.setCreateDate(new Date());
        }
        account.randomSalt();
        account.setPassword(passwordService.encryptPassword(account.getPassword(), account.getSalt()));

        return super.save(account);
    }

    private static final Account mockAccount;
    static {
    	mockAccount = new Account();
    	Email email = new Email();
    	email.setAccount(mockAccount);
    	email.setActive(true);
    	email.setEmail("admin@hunter.com");
    	email.setMain(true);
    	email.setCreateDate(new Date());
    	mockAccount.setEmails(Arrays.asList(email));
    	mockAccount.setActive(true);
    	mockAccount.setCreateDate(new Date());
    	mockAccount.setUpdateDate(new Date());
    	mockAccount.setDeleted(false);
    	mockAccount.setPassword("123456");
    }
    public Account findByEmail(String email) {
    	// TODO need to remove when it's deployed to prod
    	if("admin@hunter.com".equals(email)) {
			return mockAccount;
        }
        if(StringUtils.isEmpty(email)) {
            return null;
        }
        return getAccountRepository().findByEmail(email);
    }

    public Account login(String email, String password) {
    	// TODO need to remove when it's deployed to prod
    	if("admin@hunter.com".equals(email) && "123456".equals(password)) {
    		return mockAccount;
    	}
    	
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            UserLogUtils.log(
                    email,
                    "loginError",
                    "email or password is empty");
            throw new AccountNotExistsException();
        }
        //密码如果不在指定范围内 肯定错误
        if (password.length() < User.PASSWORD_MIN_LENGTH || password.length() > User.PASSWORD_MAX_LENGTH) {
            UserLogUtils.log(
                    email,
                    "loginError",
                    "password length error! password is between {} and {}",
                    User.PASSWORD_MIN_LENGTH, User.PASSWORD_MAX_LENGTH);
            throw new AccountPasswordNotMatchException();
        }

        Account account = null;

        //此处需要走代理对象，目的是能走缓存切面
        AccountService proxyUserService = (AccountService) AopContext.currentProxy();
        if (maybeEmail(email)) {
            account = proxyUserService.findByEmail(email);
        }

        if (account == null || Boolean.TRUE.equals(account.getDeleted())) {
            UserLogUtils.log(
                    email,
                    "loginError",
                    "user is not exists!");

            throw new AccountNotExistsException();
        }
        
        if (!account.getActive()) {
            UserLogUtils.log(
                    email,
                    "loginError",
                    "Account is not active");
            throw new AccountException("account.inactive", null);
        }

        passwordService.validate(account, password);

        UserLogUtils.log(
                email,
                "loginSuccess",
                "");
        return account;
    }

    public Account register(String email, String password, String fullName, String inviCode) {

        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password) || StringUtils.isEmpty(inviCode)) {
            UserLogUtils.log(
                    email,
                    "loginError",
                    "email or password is empty");
            throw new InvalidRegistrationInfoException();
        }
        //密码如果不在指定范围内 肯定错误
        if (password.length() < User.PASSWORD_MIN_LENGTH || password.length() > User.PASSWORD_MAX_LENGTH) {
            UserLogUtils.log(
                    email,
                    "loginError",
                    "password length error! password is between {} and {}",
                    User.PASSWORD_MIN_LENGTH, User.PASSWORD_MAX_LENGTH);
            throw new InvalidRegistrationInfoException();
        }

        if (StringUtils.isEmpty(inviCode)) {
            UserLogUtils.log(
            		inviCode,
                    "registrationError",
                    "invitation code not given");
            throw new InvitationCodeException("invicode.empty", null);
        }
        
        Account account = createAccount(email, password, fullName, inviCode);
        return account;
    }
    
    /**
     * Send verification message to mail box
     * 
     * @param email
     * @param salt
     */
    public void sendVerificationEmail(String email, String salt, String URL) {
		SimpleMailMessage msg = new SimpleMailMessage(message);
		String verificationCode = passwordService.encryptPassword(email, salt);
		msg.setTo(email);
		String link = URL + "/public/verify?code=" + verificationCode + "&email=" + email;
		msg.setText("Click below link to enable your email: " + link);
		mailSender.send(msg);
    }
    
    public String recoverPassword(String email) {
    	String messageKey = null;
        if (StringUtils.isEmpty(email)) {
        	messageKey = "email.error.nonexist";
            UserLogUtils.log(
                    email,
                    "recoverPasswordError",
                    messageKey);
        } else {
        	Account account = findValidAccountByEmail(email);
        	if(account != null) {
        		passwordService.recoverPassword(account);
        	} else {
        		messageKey = "account.invalid";
        	}
        }
        return messageKey;

    }
    /**
     * Get the account by email<p>
     * and check whether the account is active <p>
     * and check whether the account is deleted <p>
     * and check whether the email is active
     * 
     * @param email
     * @return
     */
    private Account findValidAccountByEmail(String email) {
    	Account account = findByEmail(email);
    	if(account != null && Boolean.TRUE.equals(account.getActive()) && Boolean.FALSE.equals(account.getDeleted())) {
    		for(Email accountEmail : account.getEmails()) {
    			if(accountEmail.getEmail().equals(email)) {
    				if(!Boolean.TRUE.equals(accountEmail.getActive())) {
    					account = null;
    				}
    			}
    		}
    	} else {
    		account = null;
    	}
    	return account;
    }
    
    private Account createAccount(String email, String password, String fullName, String invCode) {
        
        if (emailService.isEmailUsed(email)) {
            UserLogUtils.log(
                    email,
                    "loginError",
                    "email is already used {}",
                    email);
            throw new DuplicatedEmailRegisterException();
        }
        
        InvitationCode invitationCode = invitationCodeService.findUnusedByCode(invCode);
        if (invitationCode == null || invitationCode.getUsed()) {
            UserLogUtils.log(
            		invCode,
                    "registrationError",
                    "invitation code not found");
            throw new InvitationCodeException("invicode.notexists", new Object[] {invCode});
        }
        
        invitationCode.setUsed(true);
        invitationCodeService.saveAndFlush(invitationCode);
        
        Account account = new Account();
        account.setPassword(password);
        account.getEmails().add(new Email(account, email, true, false));
        
        People people = new People();
        people.setAccount(account);
        people.setFullName(fullName);
        people.setSelf(true);
        account.getPeople().add(people);
        
        return saveAndFlush(account);
    }
    
    private boolean maybeEmail(String email) {
        if (!email.matches(User.EMAIL_PATTERN)) {
            return false;
        }
        return true;
    }

    /******** service setter ******/
    public void setPasswordService(AccountPasswordService passwordService) {
        this.passwordService = passwordService;
    }

    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setMessage(SimpleMailMessage message) {
		this.message = message;
	}
	
	public void setPeopleService(PeopleService peopleService) {
		this.peopleService = peopleService;
	}

}
