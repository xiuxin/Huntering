package com.huntering.beans.activity.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
	
	private boolean english;
	
	@Column(name="base_knowledge")
	private boolean baseKnowledge;
	
	private boolean skill;
	
	private boolean communicate;
	
	@Column(name = "desb")
	private String description;
	
	@Enumerated(EnumType.STRING)
	@Column(name="result")
	private ResultStatus result;
	
	public static enum ResultStatus {
    	Init,
		Fail,
		Pass,
		Pending
    }
	
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

	public boolean isEnglish() {
		return english;
	}

	public void setEnglish(boolean english) {
		this.english = english;
	}

	public boolean isBaseKnowledge() {
		return baseKnowledge;
	}

	public void setBaseKnowledge(boolean baseKnowledge) {
		this.baseKnowledge = baseKnowledge;
	}

	public boolean isSkill() {
		return skill;
	}

	public void setSkill(boolean skill) {
		this.skill = skill;
	}

	public boolean isCommunicate() {
		return communicate;
	}

	public void setCommunicate(boolean communicate) {
		this.communicate = communicate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ResultStatus getResult() {
		return result;
	}

	public void setResult(ResultStatus result) {
		this.result = result;
	}
	
}
