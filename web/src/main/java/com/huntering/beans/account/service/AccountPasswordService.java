package com.huntering.beans.account.service;

import javax.annotation.PostConstruct;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.huntering.beans.account.entity.Account;
import com.huntering.beans.account.exception.AccountPasswordNotMatchException;
import com.huntering.beans.account.exception.AccountPasswordRetryLimitExceedException;
import com.huntering.common.utils.security.Md5Utils;
import com.huntering.sys.user.utils.UserLogUtils;

/**
 * 
 * @author Vincent Yao
 *
 */
@Service
public class AccountPasswordService {

    private static String prefix = "accountId";
    
    @Autowired
    private CacheManager ehcacheManager;

    private Cache loginRecordCache;

    @Value(value = "${user.password.maxRetryCount}")
    private int maxRetryCount = 10;

    public void setMaxRetryCount(int maxRetryCount) {
        this.maxRetryCount = maxRetryCount;
    }

    @PostConstruct
    public void init() {
        loginRecordCache = ehcacheManager.getCache("loginRecordCache");
    }

    public void validate(Account account, String password) {
        Long accountId = account.getId();

        int retryCount = 0;

        Element cacheElement = loginRecordCache.get(prefix + accountId);
        if (cacheElement != null) {
            retryCount = (Integer) cacheElement.getObjectValue();
            if (retryCount >= maxRetryCount) {
                UserLogUtils.log(
                        accountId.toString(),
                        "passwordError",
                        "password error, retry limit exceed! password: {},max retry count {}",
                        password, maxRetryCount);
                throw new AccountPasswordRetryLimitExceedException(maxRetryCount);
            }
        }

        if (!matches(account, password)) {
            loginRecordCache.put(new Element(prefix + accountId, ++retryCount));
            UserLogUtils.log(
                    accountId.toString(),
                    "passwordError",
                    "password error! password: {} retry count: {}",
                    password, retryCount);
            throw new AccountPasswordNotMatchException();
        } else {
            clearLoginRecordCache(prefix + accountId);
        }
    }

    public boolean matches(Account account, String newPassword) {
        return account.getPassword().equals(
        		encryptPassword(newPassword, account.getSalt()));
    }

    public void clearLoginRecordCache(String email) {
        loginRecordCache.remove(email);
    }

    public String encryptPassword(String password, String salt) {
        return Md5Utils.hash(password + salt);
    }

    public static void main(String[] args) {
        System.out.println(new AccountPasswordService().encryptPassword("123456", "iY71e4d123"));
    }
}
