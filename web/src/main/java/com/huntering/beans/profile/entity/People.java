package com.huntering.beans.profile.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

import com.huntering.beans.account.entity.Account;
import com.huntering.common.entity.BaseTimeEntity;
import com.huntering.common.plugin.entity.LogicDeleteable;
import com.huntering.common.repository.support.annotation.EnableQueryCache;

/**
 * 
 * @author Vincent Yao
 *
 */
@Entity
@Table(name = "people")
@EnableQueryCache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class People extends BaseTimeEntity<Long> implements LogicDeleteable {

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
    @Basic(optional = false, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    private Account account;
	
    private String nickName;
    
    private String fullName;
    
    private String mdn;
    
    private String country;
    
    private String city;
    
    private String district;
    
    private String desb;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;
    
    private Integer age;
   
    private Integer gender;
    
    private String summary;
    
    @Column(nullable = false)
    private boolean self;
    
    public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getMdn() {
		return mdn;
	}

	public void setMdn(String mdn) {
		this.mdn = mdn;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getDesb() {
		return desb;
	}

	public void setDesb(String desb) {
		this.desb = desb;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public boolean isSelf() {
		return self;
	}

	public void setSelf(boolean self) {
		this.self = self;
	}
    
    /**
     * 逻辑删除flag
     */
    private Boolean deleted = Boolean.FALSE;

    public People() {
    }

    public People(Long id) {
        setId(id);
    }
    
	@Override
	public Boolean getDeleted() {
		return deleted;
	}

	@Override
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
		
	}

	@Override
	public void markDeleted() {
        this.deleted = Boolean.TRUE;
	}

}
