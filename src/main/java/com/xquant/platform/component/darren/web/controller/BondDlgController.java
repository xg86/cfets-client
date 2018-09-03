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

import com.xquant.platform.component.cfets.facade.query.vo.bond.BondDialogueQuoteOrderVO;
import com.xquant.platform.component.cfets.facade.quote.bond.FacadeBondDialogueQuoteService;
import com.xquant.platform.component.darren.inner.factory.QuoteOrderInstanceFactory;
import com.xquant.platform.component.darren.util.QuoteCommonPropertiesUtil;
import com.xquant.platform.component.itf.cfets.api.dto.quote.bond.BondDialogueQuoteOrder;
import com.xquant.platform.component.itf.cfets.common.api.exception.FacadeCfetsException;

@Controller
@RequestMapping("/bond/dlg/*")
public class BondDlgController extends CommonController {

	private static final BigDecimal ceilingPrice = new BigDecimal("120");
	private static final BigDecimal varPrice = new BigDecimal("0.15");
	private static final BigDecimal floorPrice = new BigDecimal("80");
	private static final BigDecimal ceilingVolume = new BigDecimal("190000");
	private static final BigDecimal varVolume = new BigDecimal("1050");
	private static final BigDecimal floorVolume = new BigDecimal("90000");
	private static final Random rand = new Random();

	@Resource
	private FacadeBondDialogueQuoteService facadeBondDialogueQuoteService;

	@Autowired
	private QuoteOrderInstanceFactory quoteOrderInstanceFactory;

	@RequestMapping("add")
	@ResponseBody
	public ModelAndView add(HttpServletResponse response) {
		BondDialogueQuoteOrder quoteOrder = quoteOrderInstanceFactory.getBondDialogueQuoteOrder4Add();
		try {
			facadeBondDialogueQuoteService.createQuote(quoteOrder);
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
		BondDialogueQuoteOrder quoteOrder = buildQuoteOrder(request);
		// 从前台接收参数进行填充并修改
		resoleValue(request, quoteOrder);
		// 重新计算全价等参数
		QuoteCommonPropertiesUtil.fillWithCompute(computeService, quoteOrder);
		System.out
				.println("计算开始后  netPrice = " + quoteOrder.getNetPrice() + "fullPrice = " + quoteOrder.getFullPrice());
		// 此时更新时间取的是平台部中交易表的更新时间,不是trade表中的更新时间,导致更新时间不匹配,更新失败
		quoteOrder.setUpdateTime(null);
		try {
			facadeBondDialogueQuoteService.updateQuote(quoteOrder);
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
			facadeBondDialogueQuoteService.cancelQuote(buildQuoteOrder(request));
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
			facadeBondDialogueQuoteService.confirmQuote(buildQuoteOrder(request));
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
			facadeBondDialogueQuoteService.rejectQuote(buildQuoteOrder(request));
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
	private BondDialogueQuoteOrder buildQuoteOrder(HttpServletRequest request) {
		BondDialogueQuoteOrderVO queryBondDlgOrderByQuoteId = super.queryBondDlgOrderByQuoteId(
				request.getParameter("quoteid"));
		BondDialogueQuoteOrder quoteOrder = new BondDialogueQuoteOrder();
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

	private void resoleValue(HttpServletRequest request, BondDialogueQuoteOrder quoteOrder) {
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
		if (quoteOrder.getVolume().compareTo(ceilingVolume) > 0) {
			quoteOrder.setVolume(quoteOrder.getVolume().subtract(varVolume));
		} else if (quoteOrder.getVolume().compareTo(floorVolume) < 0) {
			quoteOrder.setVolume(quoteOrder.getVolume().subtract(varVolume));
		} else {
			if (rand.nextBoolean()) {
				quoteOrder.setVolume(quoteOrder.getVolume().add(varVolume));
			} else {
				quoteOrder.setVolume(quoteOrder.getVolume().subtract(varVolume));
			}
		}
	}

}
