package com.huntering.beans.account.repository;

import com.huntering.beans.account.entity.ApplyInvitationCode;
import com.huntering.common.repository.BaseRepository;
import com.huntering.common.repository.support.annotation.SearchableQuery;

/**
 * 
 * @author Vincent Yao
 *
 */
@SearchableQuery(callbackClass = AccountSearchCallback.class)
public interface ApplyInvitationCodeRepository extends BaseRepository<ApplyInvitationCode, Long> {
    
}
