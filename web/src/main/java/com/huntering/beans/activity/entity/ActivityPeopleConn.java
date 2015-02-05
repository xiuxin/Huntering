package com.huntering.beans.activity.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import com.huntering.common.plugin.entity.Stateable.PeopleRole;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.huntering.beans.profile.entity.People;
import com.huntering.common.entity.BaseEntity;
import com.huntering.common.repository.support.annotation.EnableQueryCache;

/**
 * 
 * @author Bell Qiu
 *
 */
@Entity
@Table(name = "activity_people_conn")
@EnableQueryCache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActivityPeopleConn extends BaseEntity<Long> {

	private static final long serialVersionUID = 7699094351097324172L;
	
	@ManyToOne(cascade={CascadeType.REFRESH})
	@JoinColumn(name="activity_round_id")
	private ActivityRound activityRound;
	
	@ManyToOne(cascade={CascadeType.REFRESH})
	@JoinColumn(name="people_id")
	private People people;
	
//	@ManyToOne(cascade={CascadeType.REFRESH})
//	@JoinColumn(name="people_role_id")
//	private PeopleRole peopleRole;

	@Enumerated(EnumType.STRING)
	@Column(name="people_role_id")
	private PeopleRole peopleRole;
	
	public ActivityPeopleConn() {}

	public ActivityRound getActivityRound() {
		return activityRound;
	}

	public void setActivityRound(ActivityRound activityRound) {
		this.activityRound = activityRound;
	}

	public People getPeople() {
		return people;
	}

	public void setPeople(People people) {
		this.people = people;
	}

	public PeopleRole getPeopleRole() {
		return peopleRole;
	}

	public void setPeopleRole(PeopleRole peopleRole) {
		this.peopleRole = peopleRole;
	}

}
