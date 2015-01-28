package com.huntering.beans.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.huntering.beans.account.entity.ApplyInvitationCode;
import com.huntering.beans.account.repository.ApplyInvitationCodeRepository;
import com.huntering.common.service.BaseService;
import com.huntering.sys.user.utils.UserLogUtils;

/**
 * 
 * @author Vincent Yao
 *
 */
@Service
public class ApplyInvitationCodeService extends BaseService<ApplyInvitationCode, Long> {

    @Autowired
    private ApplyInvitationCodeRepository getEmailRepository() {
        return (ApplyInvitationCodeRepository) baseRepository;
    }
    
    public String applyForInvitationCode(String email) {
    	String messageKey = null;
    	if (StringUtils.isEmpty(email)) {
	    	messageKey = "email.error.nonexist";
	        UserLogUtils.log(email, "applyForInvCodeError", messageKey);
    	} else {
    		ApplyInvitationCode appInvCd = new ApplyInvitationCode();
    		appInvCd.setEmail(email);
    		getEmailRepository().save(appInvCd);
    	}
    	
    	return messageKey;
    }
}
