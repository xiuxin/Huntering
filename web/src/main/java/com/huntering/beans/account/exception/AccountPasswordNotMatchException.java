package com.huntering.beans.account.exception;

/**
 * 
 * @author Vincent Yao
 *
 */
public class AccountPasswordNotMatchException extends AccountException {

    public AccountPasswordNotMatchException() {
        super("user.password.not.match", null);
    }
}
