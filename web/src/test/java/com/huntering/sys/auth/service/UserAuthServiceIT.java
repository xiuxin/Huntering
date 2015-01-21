/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.huntering.sys.auth.service;

import org.junit.Before;
import org.junit.Test;

import com.huntering.common.Constants;
import com.huntering.common.repository.hibernate.HibernateUtils;
import com.huntering.test.BaseIT;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 13-5-2 下午3:54
 * <p>Version: 1.0
 */
public class UserAuthServiceIT extends BaseIT {

	// Inject service
    /*@Autowired
    private UserAuthService userAuthService;
    @Autowired
    private UserService userService;*/

    @Before
    public void setUp() {
        setSqlScriptEncoding(Constants.ENCODING);
        executeSqlScript("sql/intergration-test-clear-all-data.sql", false);
        executeSqlScript("sql/intergration-test-resource-permission-role-data.sql", false);
        //clear cache
        HibernateUtils.evictLevel1Cache(entityManager);
        HibernateUtils.evictLevel2Cache(entityManager);

    }

    ///////////////////////////用户相关
    @Test
    public void testUserAuth() {
        executeSqlScript("sql/intergration-test-user-data.sql", false);

        
    }

}
