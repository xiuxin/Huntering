package com.huntering.beans.account.exception;

/**
 * 
 * @author Vincent Yao
 *
 */
public class AccountPasswordRetryLimitExceedException extends AccountException {
    public AccountPasswordRetryLimitExceedException(int retryLimitCount) {
        super("user.password.retry.limit.exceed", new Object[]{retryLimitCount});
    }
}
