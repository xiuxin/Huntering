package com.huntering.dto;

import java.io.Serializable;
import java.util.Date;

public class ActivityForm implements Serializable {

	private static final long serialVersionUID = -2444110744857662312L;
	
	private Date stratTime;
	
	private Date endTime;
	
	private String companyName;
	
	private String jobTitle;
	
	private String address;
	
	private String candidateName;
	
	private String candidateMdn;
	
	private String candidateEmail;
	
	private String interviewerName;
	
	private String interviewerMdn;
	
	private String interviewerEmail;
	
	private String participantName1;
	
	private String participantMdn1;
	
	private String participantEmail1;
	
	private String participantName2;
	
	private String participantMdn2;
	
	private String participantEmail2;
	
	private String interviewType;
	
	private String feedbackType;
	
	private String comment;

	public Date getStratTime() {
		return stratTime;
	}

	public void setStratTime(Date stratTime) {
		this.stratTime = stratTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getCandidateMdn() {
		return candidateMdn;
	}

	public void setCandidateMdn(String candidateMdn) {
		this.candidateMdn = candidateMdn;
	}

	public String getCandidateEmail() {
		return candidateEmail;
	}

	public void setCandidateEmail(String candidateEmail) {
		this.candidateEmail = candidateEmail;
	}

	public String getInterviewerName() {
		return interviewerName;
	}

	public void setInterviewerName(String interviewerName) {
		this.interviewerName = interviewerName;
	}

	public String getInterviewerMdn() {
		return interviewerMdn;
	}

	public void setInterviewerMdn(String interviewerMdn) {
		this.interviewerMdn = interviewerMdn;
	}

	public String getInterviewerEmail() {
		return interviewerEmail;
	}

	public void setInterviewerEmail(String interviewerEmail) {
		this.interviewerEmail = interviewerEmail;
	}

	public String getParticipantName1() {
		return participantName1;
	}

	public void setParticipantName1(String participantName1) {
		this.participantName1 = participantName1;
	}

	public String getParticipantMdn1() {
		return participantMdn1;
	}

	public void setParticipantMdn1(String participantMdn1) {
		this.participantMdn1 = participantMdn1;
	}

	public String getParticipantEmail1() {
		return participantEmail1;
	}

	public void setParticipantEmail1(String participantEmail1) {
		this.participantEmail1 = participantEmail1;
	}

	public String getParticipantName2() {
		return participantName2;
	}

	public void setParticipantName2(String participantName2) {
		this.participantName2 = participantName2;
	}

	public String getParticipantMdn2() {
		return participantMdn2;
	}

	public void setParticipantMdn2(String participantMdn2) {
		this.participantMdn2 = participantMdn2;
	}

	public String getParticipantEmail2() {
		return participantEmail2;
	}

	public void setParticipantEmail2(String participantEmail2) {
		this.participantEmail2 = participantEmail2;
	}

	public String getInterviewType() {
		return interviewType;
	}

	public void setInterviewType(String interviewType) {
		this.interviewType = interviewType;
	}

	public String getFeedbackType() {
		return feedbackType;
	}

	public void setFeedbackType(String feedbackType) {
		this.feedbackType = feedbackType;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
