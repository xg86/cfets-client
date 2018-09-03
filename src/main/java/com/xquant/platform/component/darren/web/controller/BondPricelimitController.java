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

import com.xquant.platform.component.cfets.facade.query.vo.bond.BondPriceLimitQuoteOrderVO;
import com.xquant.platform.component.cfets.facade.quote.bond.FacadeBondPriceLimitQuoteService;
import com.xquant.platform.component.darren.inner.factory.QuoteOrderInstanceFactory;
import com.xquant.platform.component.darren.util.QuoteCommonPropertiesUtil;
import com.xquant.platform.component.itf.cfets.api.dto.quote.bond.BondPriceLimitQuoteOrder;
import com.xquant.platform.component.itf.cfets.common.api.exception.FacadeCfetsException;

@Controller
@RequestMapping("/bond/pricelimit/*")
public class BondPricelimitController extends CommonController {

	@Resource
	private FacadeBondPriceLimitQuoteService facadeBondPriceLimitQuoteService;

	@Autowired
	private QuoteOrderInstanceFactory quoteOrderInstanceFactory;

	@RequestMapping("add")
	@ResponseBody
	public ModelAndView add(HttpServletResponse response) {
		BondPriceLimitQuoteOrder quoteOrder = quoteOrderInstanceFactory.getBondPriceLimitQuoteOrder4Add();
		try {
			facadeBondPriceLimitQuoteService.createQuote(quoteOrder);
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
			facadeBondPriceLimitQuoteService.cancelQuote(buildQuoteOrder(request));
		} catch (FacadeCfetsException e) {
			super.print(response, false);
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
	private BondPriceLimitQuoteOrder buildQuoteOrder(HttpServletRequest request) {
		BondPriceLimitQuoteOrderVO queryOrderByQuoteId = super.queryPricelimitOrderByQuoteId(
				request.getParameter("quoteid"));
		BondPriceLimitQuoteOrder quoteOrder = new BondPriceLimitQuoteOrder();
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
