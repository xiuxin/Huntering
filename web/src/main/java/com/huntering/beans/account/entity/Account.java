package com.huntering.beans.account.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.google.common.collect.Lists;
import com.huntering.common.entity.BaseTimeEntity;
import com.huntering.common.plugin.entity.LogicDeleteable;
import com.huntering.common.repository.support.annotation.EnableQueryCache;

/**
 * 
 * @author Vincent Yao
 *
 */
@Entity
@Table(name = "account")
@EnableQueryCache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Account extends BaseTimeEntity<Long> implements LogicDeleteable {
    public static final int PASSWORD_MIN_LENGTH = 5;
    public static final int PASSWORD_MAX_LENGTH = 50;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Email.class, mappedBy = "account", orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    @Basic(optional = true, fetch = FetchType.EAGER)
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    //集合缓存引起的
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)//集合缓存
    @OrderBy()
    private List<Email> emails;

    @Length(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH, message = "{user.password.not.valid}")
    private String password;

    /**
     * 加密密码时使用的种子
     */
    private String salt;
    
    private Boolean active = Boolean.FALSE;

	private String mdn;
    
    /**
     * 逻辑删除flag
     */
    private Boolean deleted = Boolean.FALSE;

    public Account() {
    }

    public Account(Long id) {
        setId(id);
    }

    public void addEmail(Email email) {
        email.setAccount(this);
        this.getEmails().add(email);
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * 生成新的种子
     */
    public void randomSalt() {
        setSalt(RandomStringUtils.randomAlphanumeric(10));
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
    public List<Email> getEmails() {
        if (emails == null) {
            emails = Lists.newArrayList();
        }
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getMdn() {
		return mdn;
	}

	public void setMdn(String mdn) {
		this.mdn = mdn;
	}
}
