package com.huntering.beans.account.exception;

/**
 * 
 * @author Vincent Yao
 *
 */
public class AccountNotExistsException extends AccountException {

    public AccountNotExistsException() {
        super("user.not.exists", null);
    }
}
