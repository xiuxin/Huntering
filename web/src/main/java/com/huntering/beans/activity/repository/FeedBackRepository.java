package com.huntering.beans.activity.repository;

import com.huntering.beans.activity.entity.FeedBack;
import com.huntering.common.repository.BaseRepository;

public interface FeedBackRepository extends BaseRepository<FeedBack, Long> {

	FeedBack findFeedBackByFeedbackCode(String feedbackCode);
	
}
