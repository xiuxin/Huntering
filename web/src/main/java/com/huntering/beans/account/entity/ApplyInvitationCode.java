package com.huntering.beans.account.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.huntering.common.entity.BaseEntity;
import com.huntering.common.entity.BaseTimeEntity;
import com.huntering.common.repository.support.annotation.EnableQueryCache;

/**
 * 
 * @author Vincent Yao
 *
 */
@Entity
@Table(name = "apply_invitation_code")
@EnableQueryCache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ApplyInvitationCode extends BaseEntity<Long> {

	@NotEmpty(message = "{not.null}")
    @Pattern(regexp = Email.EMAIL_PATTERN, message = "{user.email.not.valid}")
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "applydate")
    @Temporal(TemporalType.DATE)
    private Date applyDate;
    
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

}
