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

import com.xquant.platform.component.cfets.facade.query.vo.bond.BondMarketMakeQuoteOrderVO;
import com.xquant.platform.component.cfets.facade.quote.bond.FacadeBondMarketMakeQuoteService;
import com.xquant.platform.component.darren.inner.factory.QuoteOrderInstanceFactory;
import com.xquant.platform.component.darren.util.QuoteCommonPropertiesUtil;
import com.xquant.platform.component.itf.cfets.api.dto.quote.bond.BondClickDealQuoteOrder;
import com.xquant.platform.component.itf.cfets.api.dto.quote.bond.BondMarketMakeQuoteOrder;
import com.xquant.platform.component.itf.cfets.common.api.exception.FacadeCfetsException;

@Controller
@RequestMapping("/bond/marketmake/*")
public class BondMarketMakeController extends CommonController {

	@Resource
	private FacadeBondMarketMakeQuoteService facadeBondMarketMakeQuoteService;

	@Autowired
	private QuoteOrderInstanceFactory quoteOrderInstanceFactory;

	@RequestMapping("add")
	@ResponseBody
	public ModelAndView add(HttpServletResponse response) {
		BondMarketMakeQuoteOrder quoteOrder = quoteOrderInstanceFactory.getBondMarketMakeQuoteOrder4Add();
		try {
			facadeBondMarketMakeQuoteService.createQuote(quoteOrder);
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
			facadeBondMarketMakeQuoteService.cancelQuote(buildQuoteOrder(request));
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
	private BondMarketMakeQuoteOrder buildQuoteOrder(HttpServletRequest request) {
		BondMarketMakeQuoteOrderVO queryOrderByQuoteId = super.queryMarketMakerOrderByQuoteId(
				request.getParameter("quoteid"));
		BondMarketMakeQuoteOrder quoteOrder = new BondMarketMakeQuoteOrder();
		quoteOrder.setSellPart(new BondClickDealQuoteOrder());
		quoteOrder.setBuyPart(new BondClickDealQuoteOrder());
		quoteOrder.setQuoteId(queryOrderByQuoteId.getQuoteId());
		quoteOrder.setTransactTime(queryOrderByQuoteId.getTransactTime());
		quoteOrder.setClordidClientId(queryOrderByQuoteId.getClordidClientId());
		quoteOrder.setClrOrdId(queryOrderByQuoteId.getClrOrdId());
		quoteOrder.setOrdId(queryOrderByQuoteId.getOrdId());
		quoteOrder.setICode(queryOrderByQuoteId.getICode());
		quoteOrder.setTrdType(queryOrderByQuoteId.getTrdType());
		quoteOrder.setQuoteType(queryOrderByQuoteId.getQuoteType());
		quoteOrder.setWaitUntilTime(queryOrderByQuoteId.getWaitUntilTime());
		try {
			BeanUtils.copyProperties(quoteOrder.getBuyPart(), queryOrderByQuoteId.getBuyPart());
			BeanUtils.copyProperties(quoteOrder.getSellPart(), queryOrderByQuoteId.getSellPart());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		QuoteCommonPropertiesUtil.setRequestSideInfo(quoteOrder);
		return quoteOrder;
	}

}
