
package com.huntering.beans.account.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huntering.beans.account.entity.Account;
import com.huntering.beans.account.entity.Email;
import com.huntering.beans.account.service.AccountService;
import com.huntering.beans.account.service.ApplyInvitationCodeService;
import com.huntering.beans.account.service.EmailService;
import com.huntering.common.exception.BaseException;
import com.huntering.dto.ResponseResult;
/**
 * 
 * @author Vincent Yao
 *
 */
@Controller
@RequestMapping("/") 
public class LoginController {

    @Value(value = "${shiro.login.url}")
    private String loginUrl;

    @Autowired
    private AccountService accountService;

    @Autowired
    private EmailService emailService;
    
    @Autowired
    private MessageSource messageSource;
    
    @Autowired
    private ApplyInvitationCodeService applyInvitationCodeService;

    @RequestMapping("/login")  
    public String loginForm(HttpServletRequest request, ModelMap model)  { 
    	Subject subject = SecurityUtils.getSubject();
        if (subject != null && subject.isAuthenticated()) {
            subject.logout();
        }
        return "front/login2"; 
    }  
    
    // do not need to add mapping for /login post handler.
    // Just do the login authentication action on com.huntering.security.UserRealm.doGetAuthenticationInfo
    //@RequestMapping(value = "/login", method = RequestMethod.POST)
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
    
    @RequestMapping("/register")  
    public String register(HttpServletRequest request, ModelMap model)  { 
        return "front/login2"; 
    }  
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(
    		HttpServletRequest request,
    		@RequestParam(value = "name") String name,
    		@RequestParam(value = "email") String email, 
    		@RequestParam(value = "password") String password,
    		@RequestParam(value = "invitationCode") String invitationCode,
    		Model model) {
    	
    	try {
    		Account account = accountService.register(email, password, name, invitationCode);
        	
        	if (account != null) {
        		String url = request.getScheme() + "://" + request.getLocalAddr() + ":" +request.getLocalPort();
        		accountService.sendVerificationEmail(email, account.getSalt(), url);
        		model.addAttribute("registerSuccess", messageSource.getMessage("register.success", new Object[]{}, null));
        	}
        	
    	} catch(BaseException e) {
    		model.addAttribute("error", e.getMessage());
    	}
    	
    	return "front/login2";
    }

    @RequestMapping("/public/verify")
    public String verify(
    		HttpServletRequest request,
    		@RequestParam(value = "code") String code, 
    		@RequestParam(value = "email") String email) {
    	//TODO fuck ! not done!
    	try {
    		Email emailEntity = emailService.findByEmail(email);
        	
        	if (emailEntity != null) {
        		emailService.verifyEmail(emailEntity.getId(), code);
        		return "front/verifySuccess";
        	} else {
        		return "front/verifyFail";
        	}
        	
    	} catch(Exception e) {
    	}
    	
    	return "front/login2";
    }
    
    @RequestMapping(value = "/public/recoverpwd", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<String> recoverPassword(
    		HttpServletRequest request,
    		@RequestParam(value = "email") String email) {
    	ResponseResult<String> result = new ResponseResult<String>();
    	String messageKey = accountService.recoverPassword(email);
    	
    	if (messageKey == null) {
    		result.setSuccess(true);
    		result.setResult(messageSource.getMessage("recover.password.success", new Object[]{email}, null));
    	} else {
    		result.setSuccess(false);
    		result.setResult(messageSource.getMessage(messageKey, new Object[]{}, null));
    	}
    	
    	return result;
    }

    @RequestMapping(value = "/public/applyInvCode", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<String> applyInvitationCode(
    		HttpServletRequest request,
    		@RequestParam(value = "email") String email) {
    	
    	ResponseResult<String> result = new ResponseResult<String>();
    	String messageKey = applyInvitationCodeService.applyForInvitationCode(email);
    	
    	if (messageKey == null) {
    		result.setSuccess(true);
    		result.setResult(messageSource.getMessage("apply.invitationcode.success", new Object[]{}, null));
    	} else {
    		result.setSuccess(false);
    		result.setResult(messageSource.getMessage(messageKey, new Object[]{}, null));
    	}
    	
    	return result;
    }
    
}
