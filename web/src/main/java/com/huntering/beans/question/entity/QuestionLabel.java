package com.huntering.beans.question.entity;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.google.common.collect.Lists;
import com.huntering.common.entity.BaseEntity;
import com.huntering.common.repository.support.annotation.EnableQueryCache;

/**
 * 
 * @author Vincent Yao
 *
 */
@Entity
@Table(name = "question_label")
@EnableQueryCache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class QuestionLabel extends BaseEntity<Long> {

    private String name;
    
    private String desc;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = QuestionToLabel.class, mappedBy = "label", orphanRemoval = true)
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
}
