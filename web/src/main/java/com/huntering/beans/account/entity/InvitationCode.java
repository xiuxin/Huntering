package com.huntering.beans.account.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.huntering.common.entity.BaseTimeEntity;
import com.huntering.common.repository.support.annotation.EnableQueryCache;

/**
 * 
 * @author Vincent Yao
 *
 */
@Entity
@Table(name = "invitation_code")
@EnableQueryCache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class InvitationCode extends BaseTimeEntity<Long> {

	private String code;

    private Boolean used = Boolean.FALSE;

    public InvitationCode() {
    }

    public InvitationCode(Long id) {
        setId(id);
    }

    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Boolean getUsed() {
		return used;
	}

	public void setUsed(Boolean used) {
		this.used = used;
	}
    
}
