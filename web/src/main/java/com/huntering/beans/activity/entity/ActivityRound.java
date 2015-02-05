package com.huntering.beans.activity.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;

import com.huntering.common.entity.BaseTimeEntity;
import com.huntering.common.repository.support.annotation.EnableQueryCache;

/**
 * 
 * @author Bell Qiu
 *
 */
@Entity
@Table(name = "activity_round")
@EnableQueryCache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActivityRound extends BaseTimeEntity<Long> {
	
	private static final long serialVersionUID = 7819485175098178883L;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "start_date")
    @Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
	
	private String address;
	
	private int round = 0;
	
	private Boolean pass = Boolean.FALSE;
	
	@ManyToOne(cascade={CascadeType.REFRESH})
	@JoinColumn(name="activity_id")
	private Activity activity;
	
	@ManyToOne(cascade={CascadeType.REFRESH})
	@JoinColumn(name="activity_type_id")
	private ActivityType activityType;
	
	@OneToMany(targetEntity=ActivityPeopleConn.class, orphanRemoval=true, cascade={CascadeType.ALL}, mappedBy="activityRound")
	private List<ActivityPeopleConn> people;
	
	@OneToOne(mappedby="activityRound")
	private FeedBack feedBack;
	
	public ActivityRound() {}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public Boolean getPass() {
		return pass;
	}

	public void setPass(Boolean pass) {
		this.pass = pass;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public ActivityType getActivityType() {
		return activityType;
	}

	public void setActivityType(ActivityType activityType) {
		this.activityType = activityType;
	}

	public FeedBack getFeedBack() {
		return feedBack;
	}

	public void setFeedBack(FeedBack feedBack) {
		this.feedBack = feedBack;
	}
	
}
