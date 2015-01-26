package com.huntering.beans.profile.repository;

import java.util.List;

import com.huntering.beans.profile.entity.People;
import com.huntering.common.repository.BaseRepository;

/**
 * 
 * @author I310711
 *
 */
public interface PeopleRepository extends BaseRepository<People, Long> {
    
    List<People> findByAccountId(Long accountId);

    List<People> findByAccountIdAndSelf(Long accountId, boolean self);
    
}
