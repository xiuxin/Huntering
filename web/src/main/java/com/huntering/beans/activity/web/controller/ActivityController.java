package com.huntering.beans.activity.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.huntering.beans.account.entity.Account;
import com.huntering.beans.activity.entity.Activity;
import com.huntering.beans.activity.service.ActivityService;
import com.huntering.dto.ActivityForm;
import com.huntering.security.CurrentAccount;

/**
 * 
 * @author Bell Qiu
 *
 */
@Controller
@RequestMapping("/activity") 
public class ActivityController {
	
	@Autowired
	private ActivityService activityService;
	
	@RequestMapping("/list")
	public String listActivities(@CurrentAccount Account loginUser, Model model) {
		List<Activity> activities = activityService.findByAccountId(loginUser.getId());
        model.addAttribute("activities", activities);
		return "front/activities";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addActivity(@Valid @RequestBody ActivityForm activityForm) {
		return "front/activities";
	}

}
