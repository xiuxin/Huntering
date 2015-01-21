/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.huntering.extra.aop;

import com.huntering.common.cache.BaseCacheAspect;
import com.huntering.sys.user.entity.User;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 用户缓存切面
 * 缓存实现
 * 1、username/email/mobilePhoneNumber------>id
 * 2、id------->Model
 * <p>User: Zhang Kaitao
 * <p>Date: 13-3-22 下午9:00
 * <p>Version: 1.0
 */
//@Component
//@Aspect
public class UserCacheAspect extends BaseCacheAspect {

    public UserCacheAspect() {
        setCacheName("sys-userCache");
    }

    private String idKeyPrefix = "id-";
    private String usernameKeyPrefix = "username-";
    private String emailKeyPrefix = "email-";
    private String mobilePhoneNumberKeyPrefix = "mobilePhoneNumber-";

    ////////////////////////////////////////////////////////////////////////////////
    ////切入点
    ////////////////////////////////////////////////////////////////////////////////

    /**
     * 匹配用户Service
     */
    @Pointcut(value = "target(com.huntering.sys.user.service.UserService)")
    private void userServicePointcut() {
    }

    /**
     * only put
     * 如 新增 修改 登录 改密 改状态  只有涉及修改即可
     */
    @Pointcut(value =
            "execution(* save(..)) " +
                    "|| execution(* saveAndFlush(..)) " +
                    "|| execution(* update(..)) " +
                    "|| execution(* login(..)) " +
                    "|| execution(* changePassword(..)) " +
                    "|| execution(* changeStatus(..))")
    private void cachePutPointcut() {
    }


    /**
     * evict 比如删除
     */
    @Pointcut(value = "(execution(* delete(*))) && args(arg)", argNames = "arg")
    private void cacheEvictPointcut(Object arg) {
    }

    /**
     * put 或 get
     * 比如查询
     */
    @Pointcut(value =
            "(execution(* findByUsername(*)) " +
                    "|| execution(* findByEmail(*)) " +
                    "|| execution(* findByMobilePhoneNumber(*)) " +
                    "|| execution(* findOne(*)))")
    private void cacheablePointcut() {
    }

}
