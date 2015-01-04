package com.huntering.beans.account.service;

import java.util.Date;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.huntering.beans.account.entity.Account;
import com.huntering.beans.account.exception.AccountNotExistsException;
import com.huntering.beans.account.exception.AccountPasswordNotMatchException;
import com.huntering.beans.account.repository.AccountRepository;
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
    private AccountPasswordService passwordService;

    public void setPasswordService(AccountPasswordService passwordService) {
        this.passwordService = passwordService;
    }
    
    @Override
    public Account save(Account account) {
        if (account.getCreateDate() == null) {
            account.setCreateDate(new Date());
        }
        account.randomSalt();
        account.setPassword(passwordService.encryptPassword(account.getEmail(), account.getPassword(), account.getSalt()));

        return super.save(account);
    }

    public Account findByEmail(String email) {
        if(StringUtils.isEmpty(email)) {
            return null;
        }
        return getAccountRepository().findByEmail(email);
    }

    public Account login(String email, String password) {

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

        passwordService.validate(account, password);

        UserLogUtils.log(
                email,
                "loginSuccess",
                "");
        return account;
    }

    private boolean maybeEmail(String username) {
        if (!username.matches(User.EMAIL_PATTERN)) {
            return false;
        }
        return true;
    }

}
