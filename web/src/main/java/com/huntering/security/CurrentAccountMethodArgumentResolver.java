package com.huntering.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.huntering.beans.account.entity.Account;

/**
 * <p>Binding current user to the parameter with annotation CurrentAccount
 */
public class CurrentAccountMethodArgumentResolver implements HandlerMethodArgumentResolver {

    public CurrentAccountMethodArgumentResolver() {
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(CurrentAccount.class) && Account.class.isAssignableFrom(parameter.getParameterType())) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
    	CurrentAccount currentAccountAnnotation = parameter.getParameterAnnotation(CurrentAccount.class);
        Object attribute = null;
        HttpSession session = ((HttpServletRequest)webRequest.getNativeRequest()).getSession();
        if(session != null) {
        	attribute = session.getAttribute(currentAccountAnnotation.value());
        }
        if(attribute == null) {
        	attribute = webRequest.getAttribute(currentAccountAnnotation.value(), NativeWebRequest.SCOPE_REQUEST);
        }
		return attribute;
    }
}
