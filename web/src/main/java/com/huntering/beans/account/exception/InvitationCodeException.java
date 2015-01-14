package com.huntering.beans.account.exception;

import com.huntering.common.exception.BaseException;

/**
 * 
 * @author Vincent Yao
 *
 */
public class InvitationCodeException extends BaseException {

    public InvitationCodeException(String code, Object[] args) {
        super("inviCode", code, args, null);
    }

}
