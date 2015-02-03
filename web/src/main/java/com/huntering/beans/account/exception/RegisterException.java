package com.huntering.beans.account.exception;

import com.huntering.common.exception.BaseException;

/**
 * 
 * @author Bell Qiu
 *
 */
public class RegisterException extends BaseException {

	private static final long serialVersionUID = 7481815071272905602L;

	public RegisterException(String code, Object[] args) {
		super("register", code, args, null);
	}

}
