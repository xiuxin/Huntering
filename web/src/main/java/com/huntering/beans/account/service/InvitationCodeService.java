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

    /**
     * Check if code exists or not
     * 
     * @param code
     * @return
     */
    public boolean isAuthCodeExists(String code) {
    	return findByCode(code) == null;
    }
    
    /**
     * Find unused code by code string.
     * 
     * @param code
     * @return
     */
    public InvitationCode findUnusedByCode(String code) {
        if(StringUtils.isEmpty(code)) {
            return null;
        }
        return getAuthCodeRepository().findByCodeAndUsed(code, false);
    }

    /**
     * create invitation code, will throw exception if code exists 
     * 
     * @param code
     * @return
     */
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
