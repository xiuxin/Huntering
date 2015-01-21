/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.huntering.index.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huntering.beans.account.entity.Account;
import com.huntering.security.CurrentAccount;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 13-1-18 下午10:15
 * <p>Version: 1.0
 */
@Controller("indexController")
@RequestMapping("/")
public class IndexController {


    @RequestMapping(value = "/welcome")
    public String welcome(@CurrentAccount Account loginUser, Model model) {

        model.addAttribute("user", loginUser);

        return "admin/index/welcome";
    }
    
    @RequestMapping(value = "/")
    public String home(@CurrentAccount Account loginUser, Model model) {

        model.addAttribute("user", loginUser);

        return "admin/index/welcome";
    }





}
