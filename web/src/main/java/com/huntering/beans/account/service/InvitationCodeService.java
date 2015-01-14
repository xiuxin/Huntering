package com.huntering.beans.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.huntering.beans.account.entity.InvitationCode;
import com.huntering.beans.account.exception.AccountException;
import com.huntering.beans.account.repository.InvitationCodeRepository;
import com.huntering.common.service.BaseService;

/**
 * 
 * @author Vincent Yao
 *
 */
@Service
public class InvitationCodeService extends BaseService<InvitationCode, Long> {

    @Autowired
    private InvitationCodeRepository getAuthCodeRepository() {
        return (InvitationCodeRepository) baseRepository;
    }

    public InvitationCode findByCode(String code) {
        if(StringUtils.isEmpty(code)) {
            return null;
        }
        return getAuthCodeRepository().findByCode(code);
    }

    public boolean isAuthCodeExists(String code) {
    	return findByCode(code) == null;
    }
    
    public InvitationCode findUnusedByCode(String code) {
        if(StringUtils.isEmpty(code)) {
            return null;
        }
        return getAuthCodeRepository().findByCodeAndUsed(code, false);
    }

    public InvitationCode createAuthCode(String code) {
        
    	if (isAuthCodeExists(code)) {
    		throw new AccountException("authcode.exists", null);
    	}
        
        InvitationCode authCode = new InvitationCode();
        authCode.setCode(code);
        authCode.setUsed(false);
        return saveAndFlush(authCode);
    }
}
