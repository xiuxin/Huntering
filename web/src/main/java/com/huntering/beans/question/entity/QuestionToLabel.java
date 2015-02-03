package com.huntering.beans.question.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.huntering.common.entity.BaseEntity;
import com.huntering.common.repository.support.annotation.EnableQueryCache;

/**
 * 
 * @author Vincent Yao
 *
 */
@Entity
@Table(name = "question_to_label")
@EnableQueryCache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class QuestionToLabel extends BaseEntity<Long> {

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @Basic(optional = false, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private Question question;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @Basic(optional = false, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private QuestionLabel label;
    
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public QuestionLabel getLabel() {
        return label;
    }

    public void setLabel(QuestionLabel label) {
        this.label = label;
    }

}
