package com.xquant.platform.component.darren.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xquant.platform.component.itf.cfets.common.api.FacadeArchiveService;

/**
 * Copyright © 2018 xQuant Info. Tech Ltd. All rights reserved.
 * 
 * @Package: com.xquant.platform.component.darren.web.controller
 * @author: guanglai.zhou
 * @date: 2018-08-25 10:27:20
 */
@Controller
@RequestMapping("/cfets/*")
public class DataArchiveController extends CommonController {

	@Resource
	private FacadeArchiveService facadeArchiveService;

	@RequestMapping("archive")
	public ModelAndView archive(HttpServletRequest request, HttpServletResponse response) {
		boolean flag = true;
		try {
			facadeArchiveService.archive();
		} catch (Exception e) {
			flag = false;
		}
		super.print(response, flag);
		return null;
	}
}
