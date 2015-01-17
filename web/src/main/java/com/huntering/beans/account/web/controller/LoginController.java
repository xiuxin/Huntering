
package com.huntering.beans.account.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.huntering.beans.account.entity.Account;
import com.huntering.beans.account.service.AccountService;
import com.huntering.sys.user.service.UserStatusHistoryService;

/**
 * 
 * @author Vincent Yao
 *
 */
@Controller
public class LoginController {

    @Value(value = "${shiro.login.url}")
    private String loginUrl;

    @Autowired
    private AccountService accountService;
    
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserStatusHistoryService userStatusHistoryService;

    @RequestMapping(value = {"/public/loginPage"}, method = RequestMethod.GET)  
    public String loginForm(HttpServletRequest request, ModelMap model)  {  
        return "front/login2";  
    }  
    

    @RequestMapping("/public/login")
    public String login(@RequestParam(value = "email") String email, 
    		@RequestParam(value = "password") String password) {
    	
    	try {
    		Account account = accountService.login(email, password);
        	
        	if (account != null) {
        		return "front/success";
        	}
        	
    	} catch(Exception e) {
    	}
    	
    	return "front/login2";
    }
    
    @RequestMapping("/public/register")
    public String register(
    		@RequestParam(value = "email") String email, 
    		@RequestParam(value = "password") String password,
    		@RequestParam(value = "invitationCode") String invitationCode) {
    	
    	try {
    		Account account = accountService.register(email, password, invitationCode);
        	
        	if (account != null) {
        		return "front/registerSuccess";
        	}
        	
    	} catch(Exception e) {
    	}
    	
    	return "front/login2";
    }

}
