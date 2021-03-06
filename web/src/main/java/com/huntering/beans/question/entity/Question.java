package com.huntering.beans.question.entity;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.google.common.collect.Lists;
import com.huntering.common.entity.BaseTimeEntity;
import com.huntering.common.repository.support.annotation.EnableQueryCache;

/**
 * 
 * @author Vincent Yao
 *
 */
@Entity
@Table(name = "question")
@EnableQueryCache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Question extends BaseTimeEntity<Long> {

    private String summary;
    
    private String detail;
    
    private String scope;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @Basic(optional = false, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    private QuestionGroup questionGroup;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = QuestionToLabel.class, mappedBy = "question", orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    @Basic(optional = true, fetch = FetchType.LAZY)
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)//集合缓存
    @OrderBy()
    private List<QuestionToLabel> questionToLabels;
    
    public List<QuestionToLabel> getQuestionToLabels() {
        if (questionToLabels == null) {
            questionToLabels = Lists.newArrayList();
        }
        return questionToLabels;
    }

    public void setQuestionToLabels(List<QuestionToLabel> questionToLabels) {
        this.questionToLabels = questionToLabels;
    }
    
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public QuestionGroup getQuestionGroup() {
        return questionGroup;
    }

    public void setQuestionGroup(QuestionGroup questionGroup) {
        this.questionGroup = questionGroup;
    }

}
