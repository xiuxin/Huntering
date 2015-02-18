package com.huntering.beans.message.entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.huntering.beans.account.entity.Account;
import com.huntering.beans.activity.entity.Activity;
import com.huntering.beans.profile.entity.People;
import com.huntering.common.entity.BaseTimeEntity;
import com.huntering.common.plugin.entity.Stateable.MessageType;
import com.huntering.common.repository.support.annotation.EnableQueryCache;

/**
 * 
 * @author Vincent Yao
 *
 */
@Entity
@Table(name = "message")
@EnableQueryCache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Message extends BaseTimeEntity<Long> implements Comparable<Message> {

	private static final long serialVersionUID = 3682274210307580330L;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
    @Basic(optional = false, fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    private Account account;

	@Enumerated(EnumType.STRING)
	@Column(name="message_type")
	private MessageType type;

	@OneToOne()
    @JoinColumn(name = "activity_id", unique = false, nullable = true, updatable = true)
	private Activity activity;
	
	@OneToOne()
    @JoinColumn(name = "people_id", unique = false, nullable = true, updatable = true)
	private People people;
	
	@OneToMany(targetEntity=ActivityMessage.class, orphanRemoval=true, cascade={CascadeType.ALL}, mappedBy="message")
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy("updateDate DESC")
	private List<ActivityMessage> activityMessages = new ArrayList<ActivityMessage>();

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public People getPeople() {
		return people;
	}

	public void setPeople(People people) {
		this.people = people;
	}

	public List<ActivityMessage> getActivityMessages() {
		return activityMessages;
	}

	public void setActivityMessages(List<ActivityMessage> activityMessages) {
		this.activityMessages = activityMessages;
	}

	@Override
	public int compareTo(Message o) {
		if(o == null) {
			return -1;
		}
		if(o == this) {
			return 0;
		}
		if(this.getUpdateDate() != null && o.getUpdateDate() != null) {
			return o.getUpdateDate().compareTo(this.getUpdateDate());
		} 
		if(this.getUpdateDate() != null) {
			return -1;
		} else {
			return o.getUpdateDate() == null ? 0 : 1;
		}
	}
	
}
