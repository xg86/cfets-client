package com.xquant.platform.component.darren.web.controller;

import java.lang.reflect.InvocationTargetException;
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

import com.xquant.platform.component.cfets.facade.query.vo.xswap.XSwapQuoteOrderVO;
import com.xquant.platform.component.cfets.facade.quote.xswap.FacadeXSwapQuoteService;
import com.xquant.platform.component.darren.inner.factory.QuoteOrderInstanceFactory;
import com.xquant.platform.component.darren.util.QuoteCommonPropertiesUtil;
import com.xquant.platform.component.itf.cfets.common.api.exception.FacadeCfetsException;
import com.xquant.platform.component.itf.cfets.xswap.api.dto.XSwapQuoteOrder;
import com.xquant.platform.component.itf.cfets.xswap.api.enums.XSwapOrderCancelTypeEnum;

@Controller
@RequestMapping("/xswap/*")
public class XswapQuoteController extends CommonController {

	@Resource
	private FacadeXSwapQuoteService facadeXSwapQuoteService;

	@Autowired
	private QuoteOrderInstanceFactory quoteOrderInstanceFactory;
	
	@RequestMapping("add")
	@ResponseBody
	public ModelAndView add(HttpServletResponse response) {
		int irswap = new Random().nextDouble() < 0.5 ? 0 : 1;
		XSwapQuoteOrder quoteOrder = quoteOrderInstanceFactory.getXswapQuoteOrder4Add(irswap);
		try {
			facadeXSwapQuoteService.createQuote(quoteOrder);
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
			facadeXSwapQuoteService.cancelQuote(buildQuoteOrder(request));
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
	private XSwapQuoteOrder buildQuoteOrder(HttpServletRequest request) {
		XSwapQuoteOrderVO queryOrderByQuoteId = super.queryXswapQuoteOrderByQuoteId(request.getParameter("quoteid"));
		XSwapQuoteOrder quoteOrder = new XSwapQuoteOrder();
		try {
			BeanUtils.copyProperties(quoteOrder, queryOrderByQuoteId);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		quoteOrder.setOrderCancelType(XSwapOrderCancelTypeEnum.ALL_CANCELED);
		QuoteCommonPropertiesUtil.setRequestSideInfo4Xswap(quoteOrder);
		return quoteOrder;
	}

}
