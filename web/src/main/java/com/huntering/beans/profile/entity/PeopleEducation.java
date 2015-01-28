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

import com.huntering.common.entity.BaseTimeEntity;
import com.huntering.common.plugin.entity.LogicDeleteable;
import com.huntering.common.repository.support.annotation.EnableQueryCache;

/**
 * 
 * @author Vincent Yao
 *
 */
@Entity
@Table(name = "people_education")
@EnableQueryCache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PeopleEducation extends BaseTimeEntity<Long> implements LogicDeleteable {

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
    @Basic(optional = false, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    private People people;

    private String college;
    
    private Short degree;
    
    private String desb;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "startdate")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "enddate")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    public PeopleEducation() {
    }

    public PeopleEducation(Long id) {
        setId(id);
    }
    
    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public Short getDegree() {
        return degree;
    }

    public void setDegree(Short degree) {
        this.degree = degree;
    }

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
    
	public String getDesb() {
		return desb;
	}

	public void setDesb(String desb) {
		this.desb = desb;
	}

    /**
     * 逻辑删除flag
     */
    private Boolean deleted = Boolean.FALSE;
    
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
