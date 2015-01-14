package com.huntering.beans.account.exception;

import com.huntering.common.exception.BaseException;

/**
 * 
 * @author Vincent Yao
 *
 */
public class AccountException extends BaseException {

    public AccountException(String code, Object[] args) {
        super("authCode", code, args, null);
    }

}
