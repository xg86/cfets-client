package com.xquant.platform.component.darren.web.controller;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xquant.platform.component.cfets.facade.query.vo.bond.BondRfqReplyQuoteOrderVO;
import com.xquant.platform.component.cfets.facade.query.vo.bond.BondRfqReqQuoteOrderVO;
import com.xquant.platform.component.cfets.facade.quote.bond.FacadeBondRfqReplyQuoteService;
import com.xquant.platform.component.darren.inner.factory.QuoteOrderInstanceFactory;
import com.xquant.platform.component.darren.util.QuoteCommonPropertiesUtil;
import com.xquant.platform.component.itf.cfets.api.dto.quote.bond.BondRfqReplyQuoteOrder;
import com.xquant.platform.component.itf.cfets.api.dto.quote.bond.BondRfqReqQuoteOrder;
import com.xquant.platform.component.itf.cfets.common.api.exception.FacadeCfetsException;

@Controller
@RequestMapping("/bond/res/*")
public class BondRfqResController extends CommonController {

	@Resource
	private FacadeBondRfqReplyQuoteService facadeBondRfqReplyQuoteService;

	@Autowired
	private QuoteOrderInstanceFactory quoteOrderInstanceFactory;
	
	private static final BigDecimal ceilingPrice = new BigDecimal("120");
	private static final BigDecimal varPrice = new BigDecimal("0.15");
	private static final BigDecimal floorPrice = new BigDecimal("80");
	private static final Random rand = new Random();
	

	@RequestMapping("add")
	@ResponseBody
	public ModelAndView add(HttpServletRequest request,HttpServletResponse response) {
		BondRfqReqQuoteOrder bondRfqReqQuoteOrder = buildRfqQuoteOrder(request);
		BondRfqReplyQuoteOrder quoteOrder = quoteOrderInstanceFactory.getBondRfqReplyQuoteOrder4Add(bondRfqReqQuoteOrder);
		try {
			facadeBondRfqReplyQuoteService
					.createQuoteByMarketMaker(quoteOrder);
		} catch (FacadeCfetsException e) {
			super.print(response, false);
			return null;
		}
		super.print(response, true);
		return null;
	}

	@RequestMapping("update")
	@ResponseBody
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response) {
		
		BondRfqReplyQuoteOrder quoteOrder = buildQuoteOrder(request);
		resoleValue(request, quoteOrder);
		quoteOrder.setUpdateTime(null);
		// 重新计算全价等参数
		QuoteCommonPropertiesUtil.fillWithCompute(computeService, quoteOrder);
		try {
			facadeBondRfqReplyQuoteService.updateQuoteByMarketMaker(quoteOrder);
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
			facadeBondRfqReplyQuoteService.cancelQuoteByMarketMaker(buildQuoteOrder(request));
		} catch (FacadeCfetsException e) {
			super.print(response, false);
			return null;
		}
		super.print(response, true);
		return null;
	}

	@RequestMapping("confirm")
	@ResponseBody
	public ModelAndView confirm(HttpServletRequest request, HttpServletResponse response) {
		try {
			facadeBondRfqReplyQuoteService.confirmQuoteByNoneMarketMaker(buildQuoteOrder(request));
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
	private BondRfqReplyQuoteOrder buildQuoteOrder(HttpServletRequest request) {
		BondRfqReplyQuoteOrderVO queryBondDlgOrderByQuoteId = super.queryBondReplyOrderByQuoteId(
				request.getParameter("quoteid"));
		BondRfqReplyQuoteOrder quoteOrder = new BondRfqReplyQuoteOrder();
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
	
	/**
	 * 从数据库中查询参数填充quoteOrder
	 * 
	 * @param request
	 * @return
	 */
	private BondRfqReqQuoteOrder buildRfqQuoteOrder(HttpServletRequest request) {
		BondRfqReqQuoteOrderVO queryBondDlgOrderByQuoteId = super.queryBondRfqReqOrderByQuoteId(
				request.getParameter("quotereqid"));
		BondRfqReqQuoteOrder quoteOrder = new BondRfqReqQuoteOrder();
		try {
			BeanUtils.copyProperties(quoteOrder, queryBondDlgOrderByQuoteId);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		quoteOrder.setVolume(new BigDecimal("100000"));
		QuoteCommonPropertiesUtil.setRequestSideInfo(quoteOrder);
		return quoteOrder;
	}
	
	private void resoleValue(HttpServletRequest request, BondRfqReplyQuoteOrder quoteOrder) {
		quoteOrder.setNetPrice(new BigDecimal(request.getParameter("price")));
		quoteOrder.setVolume(new BigDecimal(request.getParameter("volume")));
		if (quoteOrder.getNetPrice().compareTo(ceilingPrice) > 0) {
			quoteOrder.setNetPrice(quoteOrder.getNetPrice().subtract(varPrice));
		} else if (quoteOrder.getNetPrice().compareTo(floorPrice) < 0) {
			quoteOrder.setNetPrice(quoteOrder.getNetPrice().add(varPrice));
		} else {
			if (rand.nextBoolean()) {
				quoteOrder.setNetPrice(quoteOrder.getNetPrice().add(varPrice));
			} else {
				quoteOrder.setNetPrice(quoteOrder.getNetPrice().subtract(varPrice));
			}
		}
	}
}
