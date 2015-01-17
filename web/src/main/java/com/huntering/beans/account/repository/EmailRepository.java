package com.huntering.beans.account.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.huntering.beans.account.entity.Account;
import com.huntering.beans.account.entity.Email;
import com.huntering.common.repository.BaseRepository;

/**
 * 
 * @author I310711
 *
 */
public interface EmailRepository extends BaseRepository<Email, Long> {
    
    Email findByEmail(String email);

    List<Email> findByAccountId(Long accoutId);
    
    List<Email> findByAccount(Account account);
    
    @Query("from Email e where e.account = ?1 and e.main = 1")
    Email findPrimaryEmailByAccount(Account account);
    
}
