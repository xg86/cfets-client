package com.xquant.platform.component.darren.web.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xquant.platform.component.darren.inner.service.WebDemoQuoteOrderService;


@Controller
@RequestMapping("/quoteOrder/")
public class ListAllQuoteOrderController {
	
	
	@Resource
	private WebDemoQuoteOrderService webDemoQuoteOrderService;
	
	@RequestMapping("list")
	public ModelAndView listAll(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("list");
		mav.addAllObjects(webDemoQuoteOrderService.getAllQuoteOrderByDate(new Date()));
		return mav;
	}

}
