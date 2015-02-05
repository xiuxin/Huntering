package com.huntering.beans.activity.web.controller;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
import com.huntering.beans.activity.entity.FeedBack;
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
import com.huntering.dto.FeedBackForm;
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
	
	private Activity saveActivityAndSendMsg(ActivityForm form, Account account, People people) {

		Company company = new Company();	
		company.setName(form.getCompanyName());
		company.setLocation(form.getAddress());
		company.setTemp(true);
		companyService.saveAndFlush(company);

		Job job = new Job();
		job.setCompany(company);
		job.setTitle(form.getJobTitle());
		jobService.saveAndFlush(job);
		
		People interviewer = savePeople(account, form.getInterviewerName(),
				form.getInterviewerMdn(), form.getInterviewerEmail());
		List<People> participants = new ArrayList<People>();
		if (StringUtils.isNotEmpty(form.getParticipantName1())) {
			participants.add(
					savePeople(account, form.getParticipantName1(),form.getParticipantMdn1(), form.getParticipantEmail1()));
		}
		if (StringUtils.isNotEmpty(form.getParticipantName2())) {
			participants.add(
					savePeople(account, form.getParticipantName2(),form.getParticipantMdn2(), form.getParticipantEmail2()));
		}
		
		ActivityRound round = new ActivityRound();
		round.setAddress(form.getAddress());
		round.setStartDate(form.getStartTime());
		round.setEndDate(form.getEndTime());
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
		activity.setJob(job);
		activity.getActivityRounds().add(round);
		activity.setDescription(form.getComment());
		round.getPeople().add(peopleConn);
		round.getPeople().add(interviewerConn);
		round.setActivity(activity);
		
		for (People participant : participants) {
			ActivityPeopleConn conn = new ActivityPeopleConn();
			conn.setActivityRound(round);
			conn.setPeople(participant);
			conn.setPeopleRole(PeopleRole.PARTICIPANT);
			round.getPeople().add(conn);
		}
		
		activityService.saveAndFlush(activity);

		messageService.sendInterviewMessage(account, activity, null);
		
		return activity;
	}
	
	@RequestMapping(value = "/{activityid}/activityround/create", method = RequestMethod.POST)
    public String addActivityRound(@CurrentAccount Account loginUser, @PathVariable("activityid") long activityId, ActivityForm activityForm) {
		Activity activity = activityService.addActivityRound(activityId, activityForm, loginUser);
		if(activity == null) {
			// TODO add error message for font end display
		} else {
			// TODO update timestamp message of the activity
		}
		return "front/activities";
	}
	
	@RequestMapping(value = "/activityround/{activityroundid}/updatefeedback", method = RequestMethod.POST)
    public String updateFeedBack(@CurrentAccount Account loginUser, @PathVariable("activityroundid") long activityRoundId, FeedBackForm feedBackForm) {
		FeedBack feedBack = activityService.updateFeedBack(activityRoundId, feedBackForm, loginUser);
		if(feedBack == null) {
			// TODO add error message for font end display
		} else {
			// TODO update timestamp message of the activity
		}
		return "front/activities";
	}
	
		
	private People savePeople(Account account, String nickName, String mdn, String email) {
		People interviewer = new People();
		interviewer.setAccount(account);
		interviewer.setMdn(mdn);
		interviewer.setFullName(nickName);
		interviewer.setEmail(email);
		interviewer.setSelf(false);
		return peopleService.saveAndFlush(interviewer);
	}
}
