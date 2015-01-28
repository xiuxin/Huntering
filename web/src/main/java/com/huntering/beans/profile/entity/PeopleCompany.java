//package com.huntering.beans.profile.entity;
//
//import java.util.Date;
//
//import javax.persistence.Basic;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//
//import org.hibernate.annotations.CacheConcurrencyStrategy;
//import org.hibernate.annotations.Fetch;
//import org.hibernate.annotations.FetchMode;
//import org.springframework.format.annotation.DateTimeFormat;
//
//import com.huntering.common.entity.BaseTimeEntity;
//import com.huntering.common.plugin.entity.LogicDeleteable;
//import com.huntering.common.repository.support.annotation.EnableQueryCache;
//
///**
// * 
// * @author Vincent Yao
// *
// */
//@Entity
//@Table(name = "PeopleCompany")
//@EnableQueryCache
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//public class PeopleCompany extends BaseTimeEntity<Long> implements LogicDeleteable {
//
//    @ManyToOne(optional = false, fetch = FetchType.LAZY)
//    @Basic(optional = false, fetch = FetchType.LAZY)
//    @Fetch(FetchMode.SELECT)
//    private People people;
//
//    @ManyToOne(optional = false, fetch = FetchType.EAGER)
//    @Basic(optional = false, fetch = FetchType.EAGER)
//    @Fetch(FetchMode.SELECT)
//    private Company company;
//    
//    private String detail;
//    
//    private Short title;
//    
//    private String desb;
//
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @Column(name = "startdate")
//    @Temporal(TemporalType.DATE)
//    private Date startDate;
//
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @Column(name = "enddate")
//    @Temporal(TemporalType.DATE)
//    private Date endDate;
//
//	public String getDesb() {
//		return desb;
//	}
//
//	public void setDesb(String desb) {
//		this.desb = desb;
//	}
//
//    public People getPeople() {
//        return people;
//    }
//
//    public void setPeople(People people) {
//        this.people = people;
//    }
//
//    public String getDetail() {
//        return detail;
//    }
//
//    public void setDetail(String detail) {
//        this.detail = detail;
//    }
//
//    public Short getTitle() {
//        return title;
//    }
//
//    public void setTitle(Short title) {
//        this.title = title;
//    }
//
//    public Date getStartDate() {
//        return startDate;
//    }
//
//    public void setStartDate(Date startDate) {
//        this.startDate = startDate;
//    }
//
//    public Date getEndDate() {
//        return endDate;
//    }
//
//    public void setEndDate(Date endDate) {
//        this.endDate = endDate;
//    }
//
//    public Company getCompany() {
//        return company;
//    }
//
//    public void setCompany(Company company) {
//        this.company = company;
//    }
//
//    /**
//     * 逻辑删除flag
//     */
//    private Boolean deleted = Boolean.FALSE;
//
//    public PeopleCompany() {
//    }
//
//    public PeopleCompany(Long id) {
//        setId(id);
//    }
//    
//	@Override
//	public Boolean getDeleted() {
//		return deleted;
//	}
//
//	@Override
//	public void setDeleted(Boolean deleted) {
//		this.deleted = deleted;
//	}
//
//	@Override
//	public void markDeleted() {
//        this.deleted = Boolean.TRUE;
//	}
//
//}
