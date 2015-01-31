package com.huntering.beans.activity.repository;

import java.util.List;

import com.huntering.beans.activity.entity.Activity;
import com.huntering.common.repository.BaseRepository;

/**
 * 
 * @author Bell Qiu
 *
 */
public interface ActivityRepository extends BaseRepository<Activity, Long> {

	List<Activity> findByAccountId(Long id);
}
