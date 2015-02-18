package com.huntering.beans.message.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.huntering.beans.activity.entity.Activity;
import com.huntering.beans.activity.entity.ActivityRound;
import com.huntering.beans.activity.entity.FeedBack;
import com.huntering.common.entity.BaseTimeEntity;
import com.huntering.common.repository.support.annotation.EnableQueryCache;

/**
 * 
 * @author Bell Qiu
 *
 */
@Entity
@Table(name = "activity_message")
@EnableQueryCache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActivityMessage extends BaseTimeEntity<Long> {
	
	private static final long serialVersionUID = 8364864634027450023L;

	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="message_id")
	private Message message;
	
	@Enumerated(EnumType.STRING)
	@Column(name="activity_message_type")
	private ActivityMessageType type;
	
	@JoinColumn(name="actvitiy_id")
	private Activity activity;
	
	@JoinColumn(name="actvitiy_round_id")
	private ActivityRound activityRound;
	
	public static enum ActivityMessageType {
    	CREATE_ACTIVITY_ROUND,
    	UPDATE_ACTIVITY_ROUND,
    	UPDATE_FEEDBACK_BY_UUID,
    	UPDATE_FEEDBACK_BY_ACCOUNT,
    	UPDATE_ACTIVITY_FEEDBACK,
    	ACITVITY_ROUND_FAIL,
    	ACITVITY_ROUND_PEENDING,
    	ACITVITY_ROUND_PASS,
    	ACITVITY_FAIL,
    	ACITVITY_PEENDING,
    	ACITVITY_PASS    	
    }

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public ActivityMessageType getType() {
		return type;
	}

	public void setType(ActivityMessageType type) {
		this.type = type;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public ActivityRound getActivityRound() {
		return activityRound;
	}

	public void setActivityRound(ActivityRound activityRound) {
		this.activityRound = activityRound;
	}

	
}
