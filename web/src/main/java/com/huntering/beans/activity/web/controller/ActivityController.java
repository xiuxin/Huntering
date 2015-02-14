package com.huntering.beans.activity.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.huntering.beans.account.entity.Account;
import com.huntering.beans.activity.entity.Activity;
import com.huntering.beans.activity.entity.FeedBack;
import com.huntering.beans.activity.service.ActivityService;
import com.huntering.beans.profile.entity.People;
import com.huntering.beans.profile.service.PeopleService;
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
	
	private final String ACTIVITY_VIEW = "redirect:/home";
	
	@Autowired
	private ActivityService activityService;
    @Autowired
    private PeopleService peopleService;
	
	@Autowired
    private MessageSource messageSource;
	
	@RequestMapping("/list")
	public String listActivities(@CurrentAccount Account loginUser, Model model) {
		List<Activity> activities = activityService.findByAccountId(loginUser.getId());
        model.addAttribute("activities", activities);
		return "front/activities";
	}
	
	@RequestMapping(value = "/add/{personId}", method = RequestMethod.POST)
    public String addActivity(@CurrentAccount Account loginUser, @PathVariable("personId") long personId, ActivityForm activityForm,
    		RedirectAttributes redirectAttributes) {
		/*activityService.addActivity(loginUser.getId(), activity));*/
		
		activityService.addActivity(activityForm, loginUser, personId);
		
		return ACTIVITY_VIEW;
	}
	
	@RequestMapping(value = "/{activityid}/activityround/create", method = RequestMethod.POST)
    public String addActivityRound(@CurrentAccount Account loginUser, @PathVariable("activityid") long activityId, ActivityForm activityForm) {
		Activity activity = activityService.addActivityRound(activityId, activityForm, loginUser);
		if(activity == null) {
			// TODO add error message for font end display
		} else {
			// TODO update timestamp message of the activity
		}
		return ACTIVITY_VIEW;
	}
	
	@RequestMapping(value = "/activityround/{activityroundid}/updatefeedback", method = RequestMethod.POST)
    public String updateFeedBack(@CurrentAccount Account loginUser, @PathVariable("activityroundid") long activityRoundId, FeedBackForm feedBackForm) {
		FeedBack feedBack = activityService.updateFeedBack(activityRoundId, feedBackForm, loginUser);
		if(feedBack == null) {
			// TODO add error message for font end display
		} else {
			// TODO update timestamp message of the activity
		}
		return ACTIVITY_VIEW;
	}
	
	@RequestMapping(value = "/updatefeedback", method = RequestMethod.GET)
    public String getFeedBackByUuid(@RequestParam("uuid") String uuid,
    		@RequestParam("peopleId") Long peopleId, Model model) {
		FeedBack feedBack = activityService.getFeedBackByUuid(uuid);
		// TODO get resume and activity summary
		if(feedBack == null) {
			model.addAttribute("message", messageSource.getMessage("feedback.notfound", null, null));
		} else {
			model.addAttribute("feedBack", feedBack);
			model.addAttribute("uuid", uuid);
	    	People people = peopleService.getPeopleProfile(peopleId);
	    	model.addAttribute("profile", people);
		}
		return "front/feedbackuuid";
	}
	
	@RequestMapping(value = "/updatefeedback", method = RequestMethod.POST)
    public String updateFeedBackByUuid(@RequestParam("uuid") String uuid, FeedBackForm feedBackForm, Model model) {
		FeedBack feedBack = activityService.updateFeedBackWithUuid(uuid, feedBackForm);
		if(feedBack == null) {
			model.addAttribute("message", messageSource.getMessage("feedback.update.fail", null, null));
		} else {
			model.addAttribute("message", messageSource.getMessage("feedback.update.success", null, null));
		}
		return "front/feedbackSuccess";
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.POST)
    public String getActivityRound(@CurrentAccount Account loginUser, @PathVariable("activityid") long activityId, ActivityForm activityForm) {
		Activity activity = activityService.addActivityRound(activityId, activityForm, loginUser);
		if(activity == null) {
			// TODO add error message for font end display
		} else {
			// TODO update timestamp message of the activity
		}
		return ACTIVITY_VIEW;
	}
	
}
