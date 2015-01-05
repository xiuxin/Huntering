package com.huntering.beans.account.exception;

/**
 * 
 * @author Vincent Yao
 *
 */
public class DuplicatedEmailRegisterException extends AccountException {

    public DuplicatedEmailRegisterException() {
        super("account.email.duplicated", null);
    }
}
