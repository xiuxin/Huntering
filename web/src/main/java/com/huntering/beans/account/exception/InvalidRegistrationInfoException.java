package com.huntering.beans.account.exception;

/**
 * 
 * @author Vincent Yao
 *
 */
public class InvalidRegistrationInfoException extends AccountException {

    public InvalidRegistrationInfoException() {
        super("account.invalid.registration", null);
    }
}
