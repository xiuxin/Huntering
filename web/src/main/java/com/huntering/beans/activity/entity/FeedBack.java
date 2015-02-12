package com.huntering.beans.activity.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.huntering.common.entity.BaseTimeEntity;
import com.huntering.common.repository.support.annotation.EnableQueryCache;

/**
 * 
 * @author Bell Qiu
 *
 */
@Entity
@Table(name = "feedback")
@EnableQueryCache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FeedBack extends BaseTimeEntity<Long> {

	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name="activity_round_id")
	private ActivityRound activityRound;
	
	private String detail;
	
	private int behavihor;
	
	private int profession;
	
	private int language;
	
	private int innovation;
	
	private int communication;
	
	private int execution;
	
	private int teamwork;
	
	private int management;
	
	private int result;
	
	private String feedbackCode;
	
	public FeedBack() {}
	
	public ActivityRound getActivityRound() {
		return activityRound;
	}

	public void setActivityRound(ActivityRound activityRound) {
		this.activityRound = activityRound;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	

	public int getBehavihor() {
		return behavihor;
	}

	public void setBehavihor(int behavihor) {
		this.behavihor = behavihor;
	}

	public int getProfession() {
		return profession;
	}

	public void setProfession(int profession) {
		this.profession = profession;
	}

	public int getLanguage() {
		return language;
	}

	public void setLanguage(int language) {
		this.language = language;
	}

	public int getInnovation() {
		return innovation;
	}

	public void setInnovation(int innovation) {
		this.innovation = innovation;
	}

	public int getCommunication() {
		return communication;
	}

	public void setCommunication(int communication) {
		this.communication = communication;
	}

	public int getExecution() {
		return execution;
	}

	public void setExecution(int execution) {
		this.execution = execution;
	}

	public int getTeamwork() {
		return teamwork;
	}

	public void setTeamwork(int teamwork) {
		this.teamwork = teamwork;
	}

	public int getManagement() {
		return management;
	}

	public void setManagement(int management) {
		this.management = management;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getFeedbackCode() {
		return feedbackCode;
	}

	public void setFeedbackCode(String feedbackCode) {
		this.feedbackCode = feedbackCode;
	}

}
