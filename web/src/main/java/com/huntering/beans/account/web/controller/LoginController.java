
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
    public String forceLogout(@RequestParam(value = "email") String email, 
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
//    @RequestMapping(value = {"/{login:login;?.*}"}) //spring3.2.2 bug see  http://jinnianshilongnian.iteye.com/blog/1831408
//    public String loginForm(HttpServletRequest request, ModelMap model) {
//
//        //表示退出
//        if (!StringUtils.isEmpty(request.getParameter("logout"))) {
//            model.addAttribute(Constants.MESSAGE, messageSource.getMessage("user.logout.success", null, null));
//        }
//
//        //表示用户删除了 @see org.apache.shiro.web.filter.user.SysUserFilter
//        if (!StringUtils.isEmpty(request.getParameter("notfound"))) {
//            model.addAttribute(Constants.ERROR, messageSource.getMessage("user.notfound", null, null));
//        }
//
//        //表示用户被管理员强制退出
//        if (!StringUtils.isEmpty(request.getParameter("forcelogout"))) {
//            model.addAttribute(Constants.ERROR, messageSource.getMessage("user.forcelogout", null, null));
//        }
//
//        //表示用户输入的验证码错误
//        if (!StringUtils.isEmpty(request.getParameter("jcaptchaError"))) {
//            model.addAttribute(Constants.ERROR, messageSource.getMessage("jcaptcha.validate.error", null, null));
//        }
//
//
//        //表示用户锁定了 @see org.apache.shiro.web.filter.user.SysUserFilter
//        if (!StringUtils.isEmpty(request.getParameter("blocked"))) {
//            User user = (User) request.getAttribute(Constants.CURRENT_USER);
//            String reason = userStatusHistoryService.getLastReason(user);
//            model.addAttribute(Constants.ERROR, messageSource.getMessage("user.blocked", new Object[]{reason}, null));
//        }
//
//        if (!StringUtils.isEmpty(request.getParameter("unknown"))) {
//            model.addAttribute(Constants.ERROR, messageSource.getMessage("user.unknown.error", null, null));
//        }
//
//        //登录失败了 提取错误消息
//        Exception shiroLoginFailureEx =
//                (Exception) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
//        if (shiroLoginFailureEx != null) {
//            model.addAttribute(Constants.ERROR, shiroLoginFailureEx.getMessage());
//        }
//
//        //如果用户直接到登录页面 先退出一下
//        //原因：isAccessAllowed实现是subject.isAuthenticated()---->即如果用户验证通过 就允许访问
//        // 这样会导致登录一直死循环
//        Subject subject = SecurityUtils.getSubject();
//        if (subject != null && subject.isAuthenticated()) {
//            subject.logout();
//        }
//
//
//        //如果同时存在错误消息 和 普通消息  只保留错误消息
//        if (model.containsAttribute(Constants.ERROR)) {
//            model.remove(Constants.MESSAGE);
//        }
//
//        return "front/login";
//    }

}
