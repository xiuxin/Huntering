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
