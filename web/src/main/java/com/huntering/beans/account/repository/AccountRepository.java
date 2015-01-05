package com.huntering.beans.account.repository;

import org.springframework.data.jpa.repository.Query;

import com.huntering.beans.account.entity.Account;
import com.huntering.common.repository.BaseRepository;
import com.huntering.common.repository.support.annotation.SearchableQuery;

/**
 * 
 * @author I310711
 *
 */
@SearchableQuery(callbackClass = AccountSearchCallback.class)
public interface AccountRepository extends BaseRepository<Account, Long> {
    
    @Query("from Account acct inner join fetch acct.emails e where e.email = ?1")
    Account findByEmail(String email);

}
