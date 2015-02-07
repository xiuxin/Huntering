package com.huntering.beans.activity.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huntering.beans.account.entity.Account;
import com.huntering.beans.activity.entity.Activity;
import com.huntering.beans.activity.entity.ActivityPeopleConn;
import com.huntering.beans.activity.entity.ActivityRound;
import com.huntering.beans.activity.entity.FeedBack;
import com.huntering.beans.activity.repository.ActivityRepository;
import com.huntering.beans.activity.repository.ActivityRoundRepository;
import com.huntering.beans.message.service.MessageService;
import com.huntering.beans.profile.entity.People;
import com.huntering.beans.profile.service.CompanyService;
import com.huntering.beans.profile.service.PeopleCompanyService;
import com.huntering.beans.profile.service.PeopleService;
import com.huntering.common.plugin.entity.Stateable.PeopleRole;
import com.huntering.common.service.BaseService;
import com.huntering.dto.ActivityForm;
import com.huntering.dto.FeedBackForm;

/**
 * 
 * @author Bell Qiu
 *
 */
@Service
public class ActivityService extends BaseService<Activity, Long> {
	
	@Autowired
	ActivityRoundRepository activityRoundReposity;
	
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

    @Autowired
    private ActivityRepository getActivityRepository() {
        return (ActivityRepository) baseRepository;
    }

    public List<Activity> findByAccountId(Long accountId) {
    	return getActivityRepository().findByAccountId(accountId);
    }
    
    public Activity addActivity(Activity activity) {
    	// TODO add activity
    	return null;
    }
    
    public ActivityRound addActivityRound(long activityId, ActivityRound activityRound) {
    	// TODO add activityRound
    	return null;
    }

	public Activity addActivityRound(long activityId, ActivityForm activityForm, Account account) {
		Activity activity = getActivityRepository().findOne(activityId);
		if(activity != null) {
			People interviewee = activity.getPeople();
			
			People interviewer = new People();
			interviewer.setAccount(account);
			interviewer.setMdn(activityForm.getInterviewerMdn());
			interviewer.setFullName(activityForm.getInterviewerName());
			interviewer.setEmail(activityForm.getInterviewerEmail());
			interviewer.setSelf(false);
			peopleService.saveAndFlush(interviewer);
			
			// new round
			ActivityRound round = new ActivityRound();
			round.setAddress(activityForm.getAddress());
			round.setStartDate(activityForm.getStartTime());
			round.setEndDate(activityForm.getEndTime());
			int maxRound = 0;
			for(ActivityRound r : activity.getActivityRounds()) {
				if(r.getRound() > maxRound) {
					maxRound = r.getRound();
				}
			}
			round.setRound(maxRound + 1);
			activity.getActivityRounds().add(round);
			round.setActivity(activity);
			
			//Interviewee
			ActivityPeopleConn peopleConn = new ActivityPeopleConn();
			peopleConn.setActivityRound(round);
			peopleConn.setPeople(interviewee);
			peopleConn.setPeopleRole(PeopleRole.INTERVIEWEE);
			
			//Interviewer
			ActivityPeopleConn interviewerConn = new ActivityPeopleConn();
			interviewerConn.setActivityRound(round);
			interviewerConn.setPeople(interviewer);
			interviewerConn.setPeopleRole(PeopleRole.INTERVIEWER);
			
			round.getPeople().add(peopleConn);
			round.getPeople().add(interviewerConn);
			
			activity = saveAndFlush(activity);
		} 
		return activity;
		
	}

	public FeedBack updateFeedBack(long activityRoundId,
			FeedBackForm feedBackForm, Account loginUser) {
		ActivityRound activityRound = activityRoundReposity.findOne(activityRoundId);
		if(activityRound != null) {
			FeedBack feedBack = activityRound.getFeedBack();
			if(feedBack == null) {
				feedBack = new FeedBack();
				feedBack.setActivityRound(activityRound);
			}
			if(StringUtils.isNotEmpty(feedBackForm.getDetail())) {
				feedBack.setDetail(feedBackForm.getDetail());
			}
			feedBack.setBehavihor(feedBackForm.getBehavihor());
			feedBack.setCommunication(feedBack.getCommunication());
			feedBack.setExecution(feedBackForm.getExecution());
			feedBack.setInnovation(feedBackForm.getInnovation());
			feedBack.setLanguage(feedBackForm.getLanguage());
			feedBack.setManagement(feedBackForm.getManagement());
			feedBack.setProfession(feedBackForm.getProfession());
			feedBack.setTeamwork(feedBackForm.getTeamwork());
			feedBack.setResult(feedBackForm.getResult());
			
			activityRoundReposity.saveAndFlush(activityRound);
			return feedBack;
		}
		return null;
	}
	
}
