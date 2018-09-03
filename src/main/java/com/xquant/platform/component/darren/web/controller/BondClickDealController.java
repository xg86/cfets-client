package com.xquant.platform.component.darren.web.controller;

import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xquant.platform.component.cfets.facade.query.vo.bond.BondClickDealQuoteOrderVO;
import com.xquant.platform.component.cfets.facade.quote.bond.FacadeBondClickDealQuoteService;
import com.xquant.platform.component.darren.inner.factory.QuoteOrderInstanceFactory;
import com.xquant.platform.component.darren.util.QuoteCommonPropertiesUtil;
import com.xquant.platform.component.itf.cfets.api.dto.quote.bond.BondClickDealQuoteOrder;
import com.xquant.platform.component.itf.cfets.common.api.exception.FacadeCfetsException;

@Controller
@RequestMapping("/bond/clickdeal/*")
public class BondClickDealController extends CommonController {

	@Resource
	private FacadeBondClickDealQuoteService facadeBondClickDealQuoteService;

	@Autowired
	private QuoteOrderInstanceFactory quoteOrderInstanceFactory;

	@RequestMapping("add")
	@ResponseBody
	public ModelAndView add(HttpServletResponse response) {
		BondClickDealQuoteOrder quoteOrder = quoteOrderInstanceFactory.getBondClickDealQuoteOrder4Add();
		try {
			facadeBondClickDealQuoteService.createQuote(quoteOrder);
		} catch (FacadeCfetsException e) {
			super.print(response, false);
			return null;
		}
		super.print(response, true);
		return null;
	}

	@RequestMapping("cancel")
	@ResponseBody
	public ModelAndView cancel(HttpServletRequest request, HttpServletResponse response) {
		try {
			facadeBondClickDealQuoteService.cancelQuote(buildQuoteOrder(request));
		} catch (FacadeCfetsException e) {
			super.print(response, false);
			return null;
		}
		super.print(response, true);
		return null;
	}

	/**
	 * 从数据库中查询参数填充quoteOrder
	 * 
	 * @param request
	 * @return
	 */
	private BondClickDealQuoteOrder buildQuoteOrder(HttpServletRequest request) {
		BondClickDealQuoteOrderVO queryOrderByQuoteId = super.queryClickDealOrderByQuoteId(
				request.getParameter("quoteid"));
		BondClickDealQuoteOrder quoteOrder = new BondClickDealQuoteOrder();
		try {
			BeanUtils.copyProperties(quoteOrder, queryOrderByQuoteId);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		QuoteCommonPropertiesUtil.setRequestSideInfo(quoteOrder);
		return quoteOrder;
	}

}
