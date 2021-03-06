/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.huntering.filter;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.huntering.beans.account.entity.Account;
import com.huntering.beans.account.service.AccountService;
import com.huntering.common.Constants;

public class SysUserFilter extends AccessControlFilter {

    @Autowired
    private AccountService accountService;

	/**
     * 用户删除了后重定向的地址
     */
    private String userNotfoundUrl;
    /**
     * 用户锁定后重定向的地址
     */
    private String userBlockedUrl;
    /**
     * 未知错误
     */
    private String userUnknownErrorUrl;
    
    public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

    public String getUserNotfoundUrl() {
        return userNotfoundUrl;
    }

    public void setUserNotfoundUrl(String userNotfoundUrl) {
        this.userNotfoundUrl = userNotfoundUrl;
    }

    public String getUserBlockedUrl() {
        return userBlockedUrl;
    }

    public void setUserBlockedUrl(String userBlockedUrl) {
        this.userBlockedUrl = userBlockedUrl;
    }

    public String getUserUnknownErrorUrl() {
        return userUnknownErrorUrl;
    }

    public void setUserUnknownErrorUrl(String userUnknownErrorUrl) {
        this.userUnknownErrorUrl = userUnknownErrorUrl;
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        if (subject == null) {
            return true;
        }

        Account user = (Account) ((HttpServletRequest) request).getSession().getAttribute(Constants.CURRENT_USER);
        if (user == null) {
        	getSubject(request, response).logout();
            return true;
        }
        
        String username = (String) subject.getPrincipal();
        //druid监控需要
        ((HttpServletRequest)request).getSession().setAttribute(Constants.CURRENT_USERNAME, username);

        return true;
    }


    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
    	Account user = (Account) ((HttpServletRequest) request).getSession().getAttribute(Constants.CURRENT_USER);
        if (user == null) {
            return true;
        }

        if (Boolean.TRUE.equals(user.getDeleted()) || Boolean.FALSE.equals(user.getActive())) {
            getSubject(request, response).logout();
            saveRequestAndRedirectToLogin(request, response);
            return false;
        }
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        getSubject(request, response).logout();
        saveRequestAndRedirectToLogin(request, response);
        return true;
    }

    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        Account user = (Account) ((HttpServletRequest) request).getSession().getAttribute(Constants.CURRENT_USER);
        String url = null;
        if (Boolean.TRUE.equals(user.getDeleted())) {
            url = getUserNotfoundUrl();
        } else if (Boolean.FALSE.equals(user.getActive())) {
            url = getUserBlockedUrl();
        } else {
            url = getUserUnknownErrorUrl();
        }
        WebUtils.issueRedirect(request, response, url);
    }

    
}
