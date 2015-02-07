package com.huntering.beans.message.service;

import java.util.Date;
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
    
    public Message addInterviewMessage(Account account, Activity activity) {
    	Message message = new Message();
    	message.setAccount(account);
    	message.setActivity(activity);
    	message.setType(MessageType.ACTIVITY);
    	save(message);
    	
    	return message;
    }
    
    public Message addResumeMessage(Account account, People people) {
    	Message message = new Message();
    	message.setAccount(account);
    	message.setPeople(people);
    	message.setType(MessageType.RESUME);
    	save(message);
    	
    	return message;
    }
    
    public Message updateMessageTime(long messageId) {
    	Message message = getMessageRepository().findOne(messageId);
    	if(message != null) {
    		message.setUpdateDate(new Date());
    		this.saveAndFlush(message);
    	}
    	return message;
    }
}
