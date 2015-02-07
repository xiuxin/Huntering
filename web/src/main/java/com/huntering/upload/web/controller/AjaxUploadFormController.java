/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.huntering.upload.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.huntering.beans.account.entity.Account;
import com.huntering.beans.message.service.MessageService;
import com.huntering.beans.profile.entity.People;
import com.huntering.beans.profile.service.PeopleService;
import com.huntering.common.Constants;
import com.huntering.common.web.upload.FileUploadUtils;
import com.huntering.dto.ResponseResult;
import com.huntering.security.CurrentAccount;
import com.huntering.upload.entity.Upload;
import com.huntering.upload.service.UploadService;

/**
 * 文件上传/下载
 * <p>User: Zhang Kaitao
 * <p>Date: 13-2-11 上午8:46
 * <p>Version: 1.0
 */
@Controller
@RequestMapping(value = "upload/ajax")
public class AjaxUploadFormController {

    @Autowired
    private UploadService uploadService;

    @Autowired
    private PeopleService peopleService;
    
    @Autowired
	private MessageService messageService;
    
    @RequestMapping(value = "create/{id}", method = RequestMethod.GET)
    public String showCreateForm( HttpServletRequest request,@CurrentAccount Account account , Model model) {
        model.addAttribute(Constants.OP_NAME, "新增");
        model.addAttribute("acctId", account.getId());
        
        People people = peopleService.createPeople( account.getId(), new People());
        
        request.getSession().setAttribute("acctId", account.getId()+"_"+String.valueOf(people.getId()));
        if (!model.containsAttribute("upload")) {
            model.addAttribute("upload", new Upload());
        }
        return "/upload/ajax/editForm";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(@Valid Upload upload, RedirectAttributes redirectAttributes) {

        uploadService.save(upload);
        redirectAttributes.addFlashAttribute(Constants.MESSAGE, "创建文件成功");
        return "redirect:/upload";
    }
    
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult<String> uploadFile(
            Model model,
            HttpServletRequest request, 
            @RequestParam(value = "file", required = false) MultipartFile file,
            @Valid @ModelAttribute("m") Upload upload,
            BindingResult result,
            @CurrentAccount Account account,
            RedirectAttributes redirectAttributes) {
    	ResponseResult<String> responseResult = new ResponseResult<String>();
        if (!file.isEmpty()) {
        	People people = peopleService.createPeople(account.getId(), new People());
        	request.getSession().setAttribute("acctId", account.getId()+"_"+String.valueOf(people.getId()));
            FileUploadUtils.upload(request, file, result);
            messageService.addResumeMessage(account, people);
            //redirectAttributes.addFlashAttribute("uploadFileMessage", "成功上传文件");
            responseResult.setSuccess(true);
            responseResult.setResult("成功上传文件");
        } else {
        	//redirectAttributes.addFlashAttribute("uploadFileMessage", "上传文件失败");
        	responseResult.setSuccess(false);
            responseResult.setResult("上传文件失败");
        }
        return responseResult;
    }

}
