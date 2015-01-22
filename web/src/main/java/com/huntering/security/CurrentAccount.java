package com.huntering.security;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.huntering.common.Constants;

/**
 * @author Bell Qiu
 *
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentAccount {
	
	/**
	 * the session name for current user.
	 * Default is Constants.CURRENT_USER
	 *
	 */
	String value() default Constants.CURRENT_USER;
}
