/**
 * 
 */
package com.xquant.platform.component.darren.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xquant.platform.component.cfets.facade.risk.FacadeXswapRiskLimitRequest;
import com.xquant.platform.component.cfets.facade.risk.FacadeXswapRiskRatioRequest;
import com.xquant.platform.component.darren.inner.factory.XswapRiskInstanceFactory;
import com.xquant.platform.component.itf.cfets.xswap.api.dto.XswapRiskLimitQueryRequest;
import com.xquant.platform.component.itf.cfets.xswap.api.dto.XswapRiskLimitSetRequest;
import com.xquant.platform.component.itf.cfets.xswap.api.dto.XswapRiskRatioQueryRequest;
import com.xquant.platform.component.itf.cfets.xswap.api.dto.XswapRiskRatioSetRequest;

/**
 * Copyright © 2018 xQuant Info. Tech Ltd. All rights reserved.
 * 
 * @Package: com.xquant.platform.component.darren.web.controller
 * @author: guanglai.zhou
 * @date: 2018-08-28 11:48:47
 */
@Controller
@RequestMapping("/xswap/risk/*")
public class XswapRiskController extends CommonController {

	@Autowired
	private FacadeXswapRiskLimitRequest facadeXswapRiskLimitRequest;

	@Autowired
	private FacadeXswapRiskRatioRequest facadeXswapRiskRatioRequest;

	@RequestMapping("limitRelationSet")
	public ModelAndView limitRelationSet(HttpServletRequest request, HttpServletResponse response) {

		XswapRiskLimitSetRequest xswapRiskLimitSetRequest = XswapRiskInstanceFactory.getRiskLimitRelationSetRequest();
		boolean flag = true;
		try {
			facadeXswapRiskLimitRequest.riskLimitSet(xswapRiskLimitSetRequest);
		} catch (Exception e) {
			flag = false;
		}
		super.print(response, flag);
		return null;
	}
	
	@RequestMapping("limitUnRelationSet")
	public ModelAndView limitUnRelationSet(HttpServletRequest request, HttpServletResponse response) {

		XswapRiskLimitSetRequest xswapRiskLimitSetRequest = XswapRiskInstanceFactory.getRiskLimitUnRelationSetRequest();
		boolean flag = true;
		try {
			facadeXswapRiskLimitRequest.riskLimitSet(xswapRiskLimitSetRequest);
		} catch (Exception e) {
			flag = false;
		}
		super.print(response, flag);
		return null;
	}

	@RequestMapping("limitAmountSet")
	public ModelAndView limitAmountSet(HttpServletRequest request, HttpServletResponse response) {

		XswapRiskLimitSetRequest xswapRiskLimitSetRequest = XswapRiskInstanceFactory.getRiskLimitAmountSetRequest();
		boolean flag = true;
		try {
			facadeXswapRiskLimitRequest.riskLimitSet(xswapRiskLimitSetRequest);
		} catch (Exception e) {
			flag = false;
		}
		super.print(response, flag);
		return null;
	}

	@RequestMapping("limitQuery")
	public ModelAndView riskLimitQuery(HttpServletRequest request, HttpServletResponse response) {

		XswapRiskLimitQueryRequest xswapRiskLimitQueryRequest = XswapRiskInstanceFactory.getRiskLimitQueryRequest();
		boolean flag = true;
		try {
			facadeXswapRiskLimitRequest.riskLimitQuery(xswapRiskLimitQueryRequest);
		} catch (Exception e) {
			flag = false;
		}
		super.print(response, flag);
		return null;
	}

	@RequestMapping("ratioSet")
	public ModelAndView riskLimitRatioSet(HttpServletRequest request, HttpServletResponse response) {

		XswapRiskRatioSetRequest xswapRiskRatioSetRequest = XswapRiskInstanceFactory.getRiskRatioSetRequest();
		boolean flag = true;
		try {
			facadeXswapRiskRatioRequest.riskRatioSet(xswapRiskRatioSetRequest);
		} catch (Exception e) {
			flag = false;
		}
		super.print(response, flag);
		return null;
	}

	@RequestMapping("ratioQuery")
	public ModelAndView riskLimitRatioQuery(HttpServletRequest request, HttpServletResponse response) {

		XswapRiskRatioQueryRequest xswapRiskRatioQueryRequest = XswapRiskInstanceFactory.getRiskRatioQueryRequest();
		boolean flag = true;
		try {
			facadeXswapRiskRatioRequest.riskRatioQuery(xswapRiskRatioQueryRequest);
		} catch (Exception e) {
			flag = false;
		}
		super.print(response, flag);
		return null;
	}

}
