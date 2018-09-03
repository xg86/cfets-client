package com.xquant.platform.component.darren.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//@Controller
@RequestMapping("/bond/recv/*")
public class MessageRecvController {

//	@Autowired
//	FacadeMessageFromCounterMock facadeMessageFromCounterMock;

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public ModelAndView recvMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mav = new ModelAndView("bond/quote");
		System.out.println(request.getParameter("message"));
//		facadeMessageFromCounterMock.handleMessage(request.getParameter("message"));
		return mav;
	}

}
