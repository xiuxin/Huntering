package com.huntering.beans.activity.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.huntering.beans.account.entity.Account;
import com.huntering.beans.activity.entity.Activity;
import com.huntering.beans.activity.entity.ActivityPeopleConn;
import com.huntering.beans.activity.entity.ActivityRound;
import com.huntering.beans.activity.entity.Job;
import com.huntering.beans.activity.service.ActivityService;
import com.huntering.beans.activity.service.JobService;
import com.huntering.beans.message.service.MessageService;
import com.huntering.beans.profile.entity.Company;
import com.huntering.beans.profile.entity.People;
import com.huntering.beans.profile.service.CompanyService;
import com.huntering.beans.profile.service.PeopleCompanyService;
import com.huntering.beans.profile.service.PeopleService;
import com.huntering.common.plugin.entity.Stateable.PeopleRole;
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
	@Autowired
	private PeopleService peopleService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private PeopleCompanyService peopleCompanyService;
	@Autowired
	private JobService jobService;
	@Autowired
	private MessageService messageService;
	
	@RequestMapping("/list")
	public String listActivities(@CurrentAccount Account loginUser, Model model) {
		List<Activity> activities = activityService.findByAccountId(loginUser.getId());
        model.addAttribute("activities", activities);
		return "front/activities";
	}
	
	@RequestMapping(value = "/add/{personId}", method = RequestMethod.POST)
    public String addActivity(@CurrentAccount Account loginUser, @PathVariable("personId") long personId, ActivityForm activityForm) {
		/*activityService.addActivity(loginUser.getId(), activity));*/
		People people = peopleService.findOne(personId);
		saveActivityAndSendMsg(activityForm, loginUser, people);
		return "front/activities";
	}
	
	private Activity saveActivityAndSendMsg(ActivityForm activityForm, Account account, People people) {

		Company company = new Company();	
		company.setName(activityForm.getCompanyName());
		companyService.saveAndFlush(company);

		Job job = new Job();
		job.setCompany(company);
		job.setTitle("job title");
		jobService.saveAndFlush(job);
		
		People interviewer = new People();
		interviewer.setAccount(account);
		interviewer.setMdn("18717776754");
		interviewer.setFullName("Vincent Yao");
		interviewer.setEmail("qiuchen_yao@126.com");
		interviewer.setSelf(false);
		peopleService.saveAndFlush(interviewer);
		
		ActivityRound round = new ActivityRound();
		round.setAddress("jinke road");
		round.setStartDate(new Date());
		round.setEndDate(new Date());
		round.setRound(1);

		//Interviewee
		ActivityPeopleConn peopleConn = new ActivityPeopleConn();
		peopleConn.setActivityRound(round);
		peopleConn.setPeople(people);
		peopleConn.setPeopleRole(PeopleRole.INTERVIEE);

		//Interviewer
		ActivityPeopleConn interviewerConn = new ActivityPeopleConn();
		interviewerConn.setActivityRound(round);
		interviewerConn.setPeople(interviewer);
		interviewerConn.setPeopleRole(PeopleRole.INTERVIEWER);
		
		Activity activity = new Activity();
		activity.setAccount(account);
		activity.setPeople(people);
		activity.getActivityRounds().add(round);
		round.getPeople().add(peopleConn);
		round.getPeople().add(interviewerConn);
		round.setActivity(activity);
		
		activityService.saveAndFlush(activity);

		messageService.sendInterviewMessage(account, activity, null);
		
		return activity;
	}
}
