/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.huntering.extra.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.huntering.common.cache.BaseCacheAspect;

/**
 * 用户权限的切面
 * <p/>
 * 1、当调用如下方法时，加缓存
 * {@link com.huntering.sys.auth.service.UserAuthService#findRoles}
 * {@link com.huntering.sys.auth.service.UserAuthService#findStringRoles}
 * {@link com.huntering.sys.auth.service.UserAuthService#findStringPermissions}
 * <p/>
 * 2、授权（Auth）
 * 当增删改授权时，
 * 如果是用户相关的，只删用户的即可，
 * 其他的全部清理
 * <p/>
 * 3。1、资源（Resource）
 * 当修改资源时判断是否发生变化（如resourceIdentity，是否显示），如果变了清缓存
 * 当删除资源时，清缓存
 * 3.2、权限（Permission）
 * 当修改权限时判断是否发生变化（如permission，是否显示），如果变了清缓存
 * 当删除权限时，清缓存
 * <p/>
 * 4、角色（Role）
 * 当删除角色时，请缓存
 * 当修改角色show/role/resourcePermissions关系时，清缓存
 * <p/>
 * 5.1、组织机构
 * 当删除/修改show/parentId字段时，清缓存
 * 5.2、工作职务
 * 当删除/修改show/parentId字段时，清缓存
 * <p/>
 * 6。1、组
 * 当修改组的默认组/show时，清缓存
 * 当删除组时，清缓存
 * <p/>
 * 6.2、当删除组关系时
 * 当新增/修改/删除的是某个用户的，只清理这个用户的缓存
 * 其他清所有缓存
 * <p/>
 * 7、用户
 * 修改时，如果组织机构/工作职务变了，仅需清自己的缓存
 * <p/>
 * 清理自己时也同时清理菜单的缓存
 * <p/>
 * TODO
 * 1、异步失效缓存
 * 2、预填充缓存（即把此刻失效的再通过异步任务查一次） 目前只查前100个吧
 * 3、加二级缓存 提高失效再查的效率
 * <p/>
 * 此方法的一个缺点就是 只要改了一个，所有缓存失效。。。。
 * TODO 思考更好的做法？
 * <p/>
 * <p>User: Zhang Kaitao
 * <p>Date: 13-5-15 下午2:16
 * <p>Version: 1.0
 */
//@Component
//@Aspect
public class UserAuthCacheAspect extends BaseCacheAspect {

    public UserAuthCacheAspect() {
        setCacheName("sys-authCache");
    }

    private String rolesKeyPrefix = "roles-";
    private String stringRolesKeyPrefix = "string-roles-";
    private String stringPermissionsKeyPrefix = "string-permissions-";

    @Autowired
    private ResourceMenuCacheAspect resourceMenuCacheAspect;

    ////////////////////////////////////////////////////////////////////////////////
    ////切入点
    ////////////////////////////////////////////////////////////////////////////////

    /**
     * 2、授权（Auth）
     * 当增删改授权时，
     * 如果是用户相关的，只删用户的即可，
     * 其他的全部清理
     */
    @Pointcut(value = "target(com.huntering.sys.auth.service.AuthService)")
    private void authServicePointcut() {
    }

    @Pointcut(value = "execution(* addGroupAuth(..)) " +
            "|| execution(* addOrganizationJobAuth(..)) " +
            "|| execution(* addOrganizationJobAuth(..))")
    private void authCacheEvictAllPointcut() {
    }

    @Pointcut(value = "(execution(* addUserAuth(*,..)) && args(arg, ..)) " +
            "|| (execution(* update(*)) && args(arg)) " +
            "|| (execution(* save(*)) && args(arg)) " +
            "|| (execution(* delete(*)) && args(arg))",
            argNames = "arg")
    private void authCacheEvictAllOrSpecialPointcut(Object arg) {
    }



}
