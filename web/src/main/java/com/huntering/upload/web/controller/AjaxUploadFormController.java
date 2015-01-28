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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.huntering.common.Constants;
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

    @RequestMapping(value = "create/{id}", method = RequestMethod.GET)
    public String showCreateForm( HttpServletRequest request, @PathVariable("id") String id , Model model) {
        model.addAttribute(Constants.OP_NAME, "新增");
        model.addAttribute("acctId", id);
        request.getSession().setAttribute("acctId", id);
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

}
