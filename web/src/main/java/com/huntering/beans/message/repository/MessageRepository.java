package com.huntering.beans.message.repository;

import java.util.List;

import com.huntering.beans.message.entity.Message;
import com.huntering.common.repository.BaseRepository;

/**
 * 
 * @author I310711
 *
 */
public interface MessageRepository extends BaseRepository<Message, Long> {
    
    List<Message> findByAccountId(Long accountId);
    
}
