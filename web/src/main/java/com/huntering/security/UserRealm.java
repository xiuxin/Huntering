/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.huntering.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.huntering.beans.account.entity.Account;
import com.huntering.beans.account.entity.Email;
import com.huntering.beans.account.service.AccountService;
import com.huntering.common.repository.support.SimpleBaseRepositoryFactoryBean;
import com.huntering.sys.user.exception.UserBlockedException;
import com.huntering.sys.user.exception.UserException;
import com.huntering.sys.user.exception.UserNotExistsException;
import com.huntering.sys.user.exception.UserPasswordNotMatchException;
import com.huntering.sys.user.exception.UserPasswordRetryLimitExceedException;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private AccountService accountService;

    private static final Logger log = LoggerFactory.getLogger("es-error");
    
    @Autowired
    public UserRealm(ApplicationContext ctx) {
        super();
        ctx.getBeansOfType(SimpleBaseRepositoryFactoryBean.class);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        /*String username = (String) principals.getPrimaryPrincipal();
        Account account = accountService.findByEmail(username);*/

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //authorizationInfo.setRoles(accountService.getRoles());
        //authorizationInfo.setStringPermissions(userAuthService.findStringPermissions(account));

        return authorizationInfo;
    }

    /*public boolean isPermitted(PrincipalCollection principals, String permission) {
        return super.isPermitted(principals, permission);
    }*/

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername().trim();
        String password = "";
        if (upToken.getPassword() != null) {
            password = new String(upToken.getPassword());
        }

        Account account = null;
        try {
            account = accountService.login(username, password);
        } catch (UserNotExistsException e) {
            throw new UnknownAccountException(e.getMessage(), e);
        } catch (UserPasswordNotMatchException e) {
            throw new AuthenticationException(e.getMessage(), e);
        } catch (UserPasswordRetryLimitExceedException e) {
            throw new ExcessiveAttemptsException(e.getMessage(), e);
        } catch (UserBlockedException e) {
            throw new LockedAccountException(e.getMessage(), e);
        } catch (Exception e) {
            log.error("login error", e);
            throw new AuthenticationException(new UserException("user.unknown.error", null));
        }
        
        String name = username;
        for(Email email : account.getEmails()) {
        	if(Boolean.TRUE.equals(email.getMain())) {
        		name = email.getEmail();
        		break;
        	}
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(name, password.toCharArray(), getName());
        return info;
    }
    
}
