/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.huntering.common.service;

import com.huntering.common.entity.User;
import com.huntering.common.repository.BaseRepository;
import com.huntering.common.service.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 13-1-17 下午7:52
 * <p>Version: 1.0
 */
@DependsOn("userRepository")
@Service()
public class UserService extends BaseService<User, Long> {

    @Autowired
    @Qualifier("userRepository")
    @Override
    public void setBaseRepository(BaseRepository<User, Long> baseRepository) {
        super.setBaseRepository(baseRepository);
    }
}
