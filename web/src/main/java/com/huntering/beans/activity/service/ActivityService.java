package com.huntering.beans.activity.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.huntering.beans.account.entity.Account;
import com.huntering.beans.activity.entity.Activity;
import com.huntering.beans.activity.entity.ActivityPeopleConn;
import com.huntering.beans.activity.entity.ActivityRound;
import com.huntering.beans.activity.entity.FeedBack;
import com.huntering.beans.activity.entity.Job;
import com.huntering.beans.activity.repository.ActivityRepository;
import com.huntering.beans.activity.repository.ActivityRoundRepository;
import com.huntering.beans.activity.repository.FeedBackRepository;
import com.huntering.beans.message.service.MessageService;
import com.huntering.beans.profile.entity.Company;
import com.huntering.beans.profile.entity.People;
import com.huntering.beans.profile.service.CompanyService;
import com.huntering.beans.profile.service.PeopleCompanyService;
import com.huntering.beans.profile.service.PeopleService;
import com.huntering.common.plugin.entity.Stateable.PeopleRole;
import com.huntering.common.service.BaseService;
import com.huntering.common.utils.ValidateUtils;
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
	FeedBackRepository feedBackRepository;
	
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
	private MailSender mailSender;
	@Autowired
	private SimpleMailMessage message;
	@Autowired
    private MessageSource messageSource;
	
	@Value("${feedback.time.limit}")
	private int feedBackTimelimit;

    @Autowired
    private ActivityRepository getActivityRepository() {
        return (ActivityRepository) baseRepository;
    }

    public List<Activity> findByAccountId(Long accountId) {
    	return getActivityRepository().findByAccountId(accountId);
    }
    
    public Activity addActivity(ActivityForm form, Account account, long personId) {
    	// TODO check the people is under current login user
    	People people = peopleService.findOne(personId);
    	if(StringUtils.isNotEmpty(form.getCandidateName())) {
			people.setFullName(form.getCandidateName());
		}
		if(StringUtils.isNotEmpty(form.getCandidatePhone())) {
			people.setMdn(form.getCandidatePhone());
		}
		if(ValidateUtils.validateEmail(form.getCandidateEmail())) {
			people.setEmail(form.getCandidateEmail());
		} else {
			//redirectAttributes.addFlashAttribute("activity_error", "Candidate Email is not valid");
			// throw exception
		}
		people = peopleService.saveAndFlush(people);
		List<String> emails = new ArrayList<String>();
		
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
		peopleConn.setPeopleRole(PeopleRole.INTERVIEWEE);

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
			if (StringUtils.isNotEmpty(participant.getEmail())) {
				emails.add(participant.getEmail());
			}
		}
		
		activity = saveAndFlush(activity);

		messageService.addInterviewMessage(account, activity);

		if (StringUtils.isNotEmpty(interviewer.getEmail())) {
			emails.add(interviewer.getEmail());
		}
		
		sendEmail(emails, form, people);
		
		return activity;
	}

	public Activity addActivityRound(long activityId, ActivityForm activityForm, Account account) {
		Activity activity = getActivityRepository().findOne(activityId);
		if(activity != null) {
			List<String> emails = new ArrayList<String>();
			People interviewee = activity.getPeople();
			
			People interviewer = savePeople(account, activityForm.getInterviewerName(),
					activityForm.getInterviewerMdn(), activityForm.getInterviewerEmail());
			if (StringUtils.isNotEmpty(activityForm.getInterviewerEmail())) {
				emails.add(activityForm.getInterviewerEmail());
			}
			List<People> participants = new ArrayList<People>();
			if (StringUtils.isNotEmpty(activityForm.getParticipantName1())) {
				participants.add(
						savePeople(account, activityForm.getParticipantName1(),activityForm.getParticipantMdn1(), activityForm.getParticipantEmail1()));
			}
			if (StringUtils.isNotEmpty(activityForm.getParticipantName2())) {
				participants.add(
						savePeople(account, activityForm.getParticipantName2(),activityForm.getParticipantMdn2(), activityForm.getParticipantEmail2()));
			}
			
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
			round.getPeople().add(peopleConn);
			
			//Interviewer
			ActivityPeopleConn interviewerConn = new ActivityPeopleConn();
			interviewerConn.setActivityRound(round);
			interviewerConn.setPeople(interviewer);
			interviewerConn.setPeopleRole(PeopleRole.INTERVIEWER);
			round.getPeople().add(interviewerConn);
			
			for (People participant : participants) {
				ActivityPeopleConn conn = new ActivityPeopleConn();
				conn.setActivityRound(round);
				conn.setPeople(participant);
				conn.setPeopleRole(PeopleRole.PARTICIPANT);
				round.getPeople().add(conn);
				if (StringUtils.isNotEmpty(participant.getEmail())) {
					emails.add(participant.getEmail());
				}
			}
			
			// add activity round feedback
			FeedBack feedBack = new FeedBack();
			feedBack.setFeedbackCode(getRandomUuidStrig());
			feedBack = feedBackRepository.saveAndFlush(feedBack);
			feedBack.setActivityRound(round);
			round.setFeedBack(feedBack);
			
			activity = saveAndFlush(activity);
			sendEmail(emails, activityForm, interviewee);
		} 
		return activity;
		
	}

	public FeedBack updateFeedBack(long activityRoundId,
			FeedBackForm feedBackForm, Account loginUser) {
		// TODO check the ativity round is under current login user
		ActivityRound activityRound = activityRoundReposity.findOne(activityRoundId);
		if(activityRound != null) {
			FeedBack feedBack = activityRound.getFeedBack();
			if(feedBack == null) {
				feedBack = new FeedBack();
				feedBack.setFeedbackCode(getRandomUuidStrig());
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
	
	public FeedBack getFeedBackByUuid(String uuid) {
		if(StringUtils.isEmpty(uuid)) {
		} else {
			FeedBack feedBack = feedBackRepository.findFeedBackByFeedbackCode(uuid);
			if(feedBack != null) {
				ActivityRound activityRound = feedBack.getActivityRound();
				if((activityRound.getStartDate().getTime() + feedBackTimelimit) >= System.currentTimeMillis()) {
					return feedBack;
				} else {
					// TODO the feedback uuid is time out.
				}
			}
		}
		return null;
	}
	
	public FeedBack updateFeedBackWithUuid(String uuid,
			FeedBackForm feedBackForm) {
		FeedBack feedBack = getFeedBackByUuid(uuid);
		if(feedBack != null) {
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
			
			feedBack = feedBackRepository.saveAndFlush(feedBack);
			return feedBack;
		}
		return null;
	}
	
	private void sendEmail(List<String> managerMail, ActivityForm form, People people) {
		SimpleMailMessage candidateMsg = new SimpleMailMessage(message);
		SimpleMailMessage managerMsg = new SimpleMailMessage(message);
		
		candidateMsg.setSubject(messageSource.getMessage("activity.subject", new Object[]{}, null));
		managerMsg.setSubject(messageSource.getMessage("activity.subject", new Object[]{}, null));
		
		candidateMsg.setTo(people.getEmail());
		candidateMsg.setText(people.getFullName() + "面试安排如下："
				+ "面试时间" + form.getStartTime() 
				+ "面试地址" + form.getAddress()
				+ "面试公司" + form.getCompanyName());
//		(messageSource.getMessage("activity.candidate.content", 
//				new Object[]{people.getNickName(), form.getStartTime(), form.getAddress(), form.getCompanyName()}, null));
		
		managerMsg.setTo(managerMail.toArray(new String[]{}));
		managerMsg.setText(people.getFullName() + "面试安排如下："
				+ "面试时间" + form.getStartTime() 
				+ "面试地址" + form.getAddress()
				+ "面试公司" + form.getCompanyName());
//		managerMsg.setSubject(messageSource.getMessage("activity.candidate.content", 
//				new Object[]{people.getNickName(), form.getStartTime(), form.getAddress(), form.getCompanyName()}, null));
		
		mailSender.send(new SimpleMailMessage[] {candidateMsg, managerMsg});
//		mailSender.send(candidateMsg);
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

	public static String getRandomUuidStrig() {
		return UUID.randomUUID().toString();
	}
}
