/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.huntering.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;

import com.huntering.beans.account.entity.Account;
import com.huntering.beans.account.service.AccountService;
import com.huntering.common.Constants;

/**
 * 基于几点修改：
 * 1、onLoginFailure 时 把异常添加到request attribute中 而不是异常类名
 * 2、登录成功时：成功页面重定向：
 * 2.1、如果前一个页面是登录页面，-->2.3
 * 2.2、如果有SavedRequest 则返回到SavedRequest
 * 2.3、否则根据当前登录的用户决定返回到管理员首页/前台首页
 * <p/>
 */
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {

	@Autowired
    private AccountService accountService;

    @Override
    protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
        request.setAttribute(getFailureKeyAttribute(), ae);
    }

    /**
     * 默认的成功地址
     */
    private String defaultSuccessUrl;
    /**
     * 管理员默认的成功地址
     */
    private String adminDefaultSuccessUrl;

    public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public void setDefaultSuccessUrl(String defaultSuccessUrl) {
        this.defaultSuccessUrl = defaultSuccessUrl;
    }

    public void setAdminDefaultSuccessUrl(String adminDefaultSuccessUrl) {
        this.adminDefaultSuccessUrl = adminDefaultSuccessUrl;
    }

    public String getDefaultSuccessUrl() {
        return defaultSuccessUrl;
    }

    public String getAdminDefaultSuccessUrl() {
        return adminDefaultSuccessUrl;
    }

    /**
     * 根据用户选择成功地址
     *
     * @return
     */
    @Override
    public String getSuccessUrl() {
        //String username = (String) SecurityUtils.getSubject().getPrincipal();
        //Account account = accountService.findByEmail(username);
        /*if (account != null && Boolean.TRUE.equals(account.isAdmin())) {
            return getAdminDefaultSuccessUrl();
        }*/
        return getDefaultSuccessUrl();
    }

	@Override
	protected boolean onLoginSuccess(AuthenticationToken token,
			Subject subject, ServletRequest request, ServletResponse response)
			throws Exception {
		String username = (String)token.getPrincipal();
		Account user = accountService.findByEmail(username);
		((HttpServletRequest) request).getSession().setAttribute(Constants.CURRENT_USER, user);
		((HttpServletRequest) request).getSession().setAttribute(Constants.CURRENT_USER, user);
		return super.onLoginSuccess(token, subject, request, response);
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {
		((HttpServletRequest) request).getSession().removeAttribute(Constants.CURRENT_USER);
		return super.onAccessDenied(request, response);
	}

	@Override
	protected boolean onLoginFailure(AuthenticationToken token,
			AuthenticationException e, ServletRequest request,
			ServletResponse response) {
		((HttpServletRequest) request).getSession().removeAttribute(Constants.CURRENT_USER);
		return super.onLoginFailure(token, e, request, response);
	}
	
}
