package com.huntering.beans.activity.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.huntering.beans.profile.entity.Company;
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
@Table(name = "job")
@EnableQueryCache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Job extends BaseTimeEntity<Long> implements Stateable<AuditStatus> {
	
	private static final long serialVersionUID = 893089013356680800L;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	@JoinColumn(name="company_id")
	private Company company;
	
	private String title;
	
	@Column(name = "desb")
	private String destripition;
	
	private String requirement;
    
	@Enumerated(EnumType.STRING)
	@Column(name="status")
	private AuditStatus status = AuditStatus.ACTIVE;
	
	public static enum Status {
		ACTIVE,
		DELETED,
		DISABLED
	}
	
	public Job(){
		// for hibernate
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDestripition() {
		return destripition;
	}

	public void setDestripition(String destripition) {
		this.destripition = destripition;
	}

	public String getRequirement() {
		return requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public void setStatus(Status status) {
		
	}

	@Override
	public void setStatus(AuditStatus status) {
		this.status = status;
		
	}

	@Override
	public AuditStatus getStatus() {
		return status;
	}

}
