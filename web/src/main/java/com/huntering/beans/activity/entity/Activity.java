package com.huntering.beans.activity.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.huntering.beans.profile.entity.People;
import com.huntering.common.entity.BaseTimeEntity;
import com.huntering.common.plugin.entity.Stateable;
import com.huntering.common.plugin.entity.Stateable.AuditStatus;
import com.huntering.common.repository.support.annotation.EnableQueryCache;

/**
 * 
 * @author Bell Qiu
 *
 */
@Entity
@Table(name = "activity")
@EnableQueryCache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Activity extends BaseTimeEntity<Long> implements Stateable<AuditStatus> {
	
	private static final long serialVersionUID = -7881258799492526522L;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	@JoinColumn(name="job_id")
	private Job job;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	@JoinColumn(name="peopel_id")
	private People people;

	private String feedback;
	
	@Column(name = "desb")
	private String description;
	
	private Boolean pass = Boolean.FALSE;
	
	private AuditStatus status = AuditStatus.ACTIVE;
	
	public Activity() {}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public People getPeople() {
		return people;
	}

	public void setPeople(People people) {
		this.people = people;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getPass() {
		return pass;
	}

	public void setPass(Boolean pass) {
		this.pass = pass;
	}

	@Override
	public void setStatus(
			AuditStatus status) {
		this.status = status;
	}

	@Override
	public AuditStatus getStatus() {
		return status;
	}
}
