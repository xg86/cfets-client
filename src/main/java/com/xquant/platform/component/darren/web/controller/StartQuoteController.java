package com.xquant.platform.component.darren.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/quote/*")
@Controller
public class StartQuoteController {

	  @RequestMapping("start")
	  public ModelAndView startQuote() {
		  ModelAndView mav = new ModelAndView("bond/quote");
		  return mav;
	  }
}
