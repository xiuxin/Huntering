package com.huntering.beans.account.repository;

import com.huntering.beans.account.entity.InvitationCode;
import com.huntering.common.repository.BaseRepository;

/**
 * 
 * @author I310711
 *
 */
public interface InvitationCodeRepository extends BaseRepository<InvitationCode, Long> {
    

    InvitationCode findByCode(String code);
    
    InvitationCode findByCodeAndUsed(String code, boolean used);

}
