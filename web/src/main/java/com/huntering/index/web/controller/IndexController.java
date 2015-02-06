/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.huntering.index.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huntering.beans.account.entity.Account;
import com.huntering.beans.account.service.AccountService;
import com.huntering.beans.activity.service.ActivityService;
import com.huntering.beans.message.entity.Message;
import com.huntering.beans.message.service.MessageService;
import com.huntering.beans.profile.service.PeopleService;
import com.huntering.security.CurrentAccount;

@Controller("indexController")
@RequestMapping("/")
public class IndexController {

	@Autowired
	private ActivityService activityService;
	
	@Autowired
    private AccountService accountService;
	
	@Autowired
	private PeopleService peopleService;
	
    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/welcome")
    public String welcome(@CurrentAccount Account loginUser, Model model) {

        model.addAttribute("user", loginUser);

        return "admin/index/welcome";
    }
    
    @RequestMapping(value = "/home")
    public String home(@CurrentAccount Account loginUser, Model model) {

    	List<Message> messages = messageService.findByAccountId(loginUser.getId());
        model.addAttribute("user", loginUser);
        model.addAttribute("messages", messages);

        return "front/home";
    }
    
    @RequestMapping(value = "/")
    public String homeIndex(@CurrentAccount Account loginUser, Model model) {

    	List<Message> messages = messageService.findByAccountId(loginUser.getId());
        model.addAttribute("user", loginUser);
        model.addAttribute("messages", messages);

        return "front/home";
    }


}
