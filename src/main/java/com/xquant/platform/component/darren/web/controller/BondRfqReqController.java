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

import com.xquant.platform.component.cfets.facade.query.vo.bond.BondRfqReqQuoteOrderVO;
import com.xquant.platform.component.cfets.facade.quote.bond.FacadeBondRfqReqQuoteService;
import com.xquant.platform.component.darren.inner.factory.QuoteOrderInstanceFactory;
import com.xquant.platform.component.darren.util.QuoteCommonPropertiesUtil;
import com.xquant.platform.component.itf.cfets.api.dto.quote.bond.BondRfqReqQuoteOrder;
import com.xquant.platform.component.itf.cfets.common.api.exception.FacadeCfetsException;

@Controller
@RequestMapping("/bond/rfq/*")
public class BondRfqReqController extends CommonController {

	@Resource
	private FacadeBondRfqReqQuoteService facadeBondRfqReqQuoteService;

	@Autowired
	private QuoteOrderInstanceFactory quoteOrderInstanceFactory;

	@RequestMapping("add")
	@ResponseBody
	public ModelAndView add(HttpServletResponse response) {
		BondRfqReqQuoteOrder quoteOrder = quoteOrderInstanceFactory.getBondRfqReqQuoteOrder4Add();
		try {
			facadeBondRfqReqQuoteService.createQuoteByNoneMarketMaker(quoteOrder);
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
			facadeBondRfqReqQuoteService.cancelQuoteByNoneMarketMaker(buildQuoteOrder(request));
		} catch (FacadeCfetsException e) {
			super.print(response, false);
			return null;
		}
		super.print(response, true);
		return null;
	}

	@RequestMapping("reject")
	@ResponseBody
	public ModelAndView reject(HttpServletRequest request, HttpServletResponse response) {
		try {
			facadeBondRfqReqQuoteService.rejectQuoteByMarketMaker(buildQuoteOrder(request));
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
	private BondRfqReqQuoteOrder buildQuoteOrder(HttpServletRequest request) {
		BondRfqReqQuoteOrderVO queryBondDlgOrderByQuoteId = super.queryBondRfqReqOrderByQuoteId(
				request.getParameter("quoteid"));
		BondRfqReqQuoteOrder quoteOrder = new BondRfqReqQuoteOrder();
		try {
			BeanUtils.copyProperties(quoteOrder, queryBondDlgOrderByQuoteId);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		QuoteCommonPropertiesUtil.setRequestSideInfo(quoteOrder);
		return quoteOrder;
	}

}
