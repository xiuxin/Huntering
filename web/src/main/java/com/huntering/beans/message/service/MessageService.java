package com.huntering.beans.message.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huntering.beans.account.entity.Account;
import com.huntering.beans.activity.entity.Activity;
import com.huntering.beans.message.entity.Message;
import com.huntering.beans.message.repository.MessageRepository;
import com.huntering.beans.profile.entity.People;
import com.huntering.common.plugin.entity.Stateable.MessageType;
import com.huntering.common.service.BaseService;

/**
 * 
 * @author Vincent Yao
 *
 */
@Service
public class MessageService extends BaseService<Message, Long> {

    @Autowired
    private MessageRepository getMessageRepository() {
        return (MessageRepository) baseRepository;
    }

    public List<Message> findByAccountId(Long accountId) {
    	return getMessageRepository().findByAccountId(accountId);
    }
    
    public Message sendInterviewMessage(Account account, Activity activity, People people) {
    	Message message = new Message();
    	message.setAccount(account);
    	message.setActivity(activity);
    	message.setPeople(people);
    	message.setType(MessageType.ACTIVITY);
    	save(message);
    	
    	return message;
    }
}
