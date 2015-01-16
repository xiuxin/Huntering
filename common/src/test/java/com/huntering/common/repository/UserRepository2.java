/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.huntering.common.repository;

import com.huntering.common.entity.BaseInfo;
import com.huntering.common.entity.SchoolInfo;
import com.huntering.common.entity.User;
import com.huntering.common.entity.search.Searchable;
import com.huntering.common.repository.BaseRepository;

import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;

import java.util.List;


/**
 * <p>用户仓库</p>
 * <p>User: Zhang Kaitao
 * <p>Date: 13-1-14 下午2:18
 * <p>Version: 1.0
 */
public interface UserRepository2 extends BaseRepository<User, Long> {


    ////////////////////////////////////////////////////
    /////////以下实现都委托给UserRepository2Impl///////
    ////////////////////////////////////////////////////

    public BaseInfo findBaseInfoById(Long id);

    public List<SchoolInfo> findAllSchoolTypeById(Long id);

    /*public Page<User> findAllByDefault(final Searchable searchable);

    public long countAllByDefault(final Searchable searchable);

    public long countAllByCustom(final Searchable searchable);

    public Page<User> findAllByCustom(final Searchable searchable);*/

}
