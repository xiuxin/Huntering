package com.huntering.beans.account.repository;

import com.huntering.beans.account.entity.Email;
import com.huntering.common.repository.BaseRepository;
import com.huntering.common.repository.support.annotation.SearchableQuery;

/**
 * 
 * @author I310711
 *
 */
@SearchableQuery(callbackClass = AccountSearchCallback.class)
public interface EmailRepository extends BaseRepository<Email, Long> {
    
    Email findByEmail(String email);

}
