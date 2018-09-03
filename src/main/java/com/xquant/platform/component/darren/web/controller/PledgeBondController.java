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

import com.xquant.platform.component.cfets.facade.query.vo.pledgerepo.PledgeRepoDialogueQuoteOrderVO;
import com.xquant.platform.component.cfets.facade.quote.pledgerepo.FacadePledgeRepoDialogueQuoteService;
import com.xquant.platform.component.darren.inner.factory.QuoteOrderInstanceFactory;
import com.xquant.platform.component.darren.util.QuoteCommonPropertiesUtil;
import com.xquant.platform.component.entity.instrument.PledgeBond;
import com.xquant.platform.component.itf.cfets.api.dto.quote.pledgerepo.PledgeRepoDialogueQuoteOrder;
import com.xquant.platform.component.itf.cfets.common.api.exception.FacadeCfetsException;

@Controller
@RequestMapping("/pledge/dlg/*")
public class PledgeBondController extends CommonController {

	private static final BigDecimal ceilingPrice = new BigDecimal("0.80");
	private static final BigDecimal varPrice = new BigDecimal("0.15");
	private static final BigDecimal floorPrice = new BigDecimal("0.30");
	private static final BigDecimal ceilingVolume = new BigDecimal("50000000");
	private static final BigDecimal varVolume = new BigDecimal("2000000");
	private static final BigDecimal floorVolume = new BigDecimal("10000000");
	private static final Random rand = new Random();
	
	@Resource
	private FacadePledgeRepoDialogueQuoteService facadePledgeRepoDialogueQuoteService;

	@Autowired
	private QuoteOrderInstanceFactory quoteOrderInstanceFactory;

	@RequestMapping("add")
	@ResponseBody
	public ModelAndView add(HttpServletResponse response) {
		PledgeRepoDialogueQuoteOrder quoteOrder = quoteOrderInstanceFactory.getPledgeRepoDialogueQuoteOrder4Add();
		try {
			facadePledgeRepoDialogueQuoteService.createQuote(quoteOrder);
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
		PledgeRepoDialogueQuoteOrder quoteOrder = buildQuoteOrder(request);
		// 从前台接收参数进行填充并修改
		quoteOrder.setRate(new BigDecimal(request.getParameter("price")));
		quoteOrder.setVolume(new BigDecimal(request.getParameter("volume")));
		changeValue(quoteOrder);
		QuoteCommonPropertiesUtil.fillWithCompute4Repo(computeService, quoteOrder, quoteOrder.getPledgeBonds());
		// 此时更新时间取的是平台部中交易表的更新时间,不是trade表中的更新时间,导致更新时间不匹配,更新失败
		quoteOrder.setUpdateTime(null);
		try {
			facadePledgeRepoDialogueQuoteService.updateQuote(quoteOrder);
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
			facadePledgeRepoDialogueQuoteService.cancelQuote(buildQuoteOrder(request));
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
			facadePledgeRepoDialogueQuoteService.confirmQuote(buildQuoteOrder(request));
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
			facadePledgeRepoDialogueQuoteService.rejectQuote(buildQuoteOrder(request));
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
	private PledgeRepoDialogueQuoteOrder buildQuoteOrder(HttpServletRequest request) {
		PledgeRepoDialogueQuoteOrderVO queryBondDlgOrderByQuoteId = super.queryPledgeOrderByQuoteId(
				request.getParameter("quoteid"));
		PledgeRepoDialogueQuoteOrder quoteOrder = new PledgeRepoDialogueQuoteOrder();
		quoteOrder.setRate(queryBondDlgOrderByQuoteId.getRate().divide(new BigDecimal("100")));
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
	
	private void changeValue(PledgeRepoDialogueQuoteOrder quoteOrder) {
		if (quoteOrder.getRate().compareTo(ceilingPrice) > 0) {
			quoteOrder.setRate(quoteOrder.getRate().subtract(varPrice));
		} else if (quoteOrder.getRate().compareTo(floorPrice) < 0) {
			quoteOrder.setRate(quoteOrder.getRate().add(varPrice));
		} else {
			if (rand.nextBoolean()) {
				quoteOrder.setRate(quoteOrder.getRate().add(varPrice));
			} else {
				quoteOrder.setRate(quoteOrder.getRate().subtract(varPrice));
			}
		}
		
		for(PledgeBond bond:quoteOrder.getPledgeBonds()) {
			if (bond.getAmount().compareTo(ceilingVolume) > 0) {
				bond.setAmount(bond.getAmount().subtract(varVolume));
			} else if (bond.getAmount().compareTo(floorVolume) < 0) {
				bond.setAmount(bond.getAmount().add(varVolume));
			} else {
				if (rand.nextBoolean()) {
					bond.setAmount(bond.getAmount().add(varVolume));
				} else {
					bond.setAmount(bond.getAmount().subtract(varVolume));
				}
			}
		}
	}

}
