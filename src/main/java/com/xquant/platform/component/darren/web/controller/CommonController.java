package com.xquant.platform.component.darren.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xquant.platform.component.cfets.facade.query.namespace.BondClickDealQuoteOrderNamespace;
import com.xquant.platform.component.cfets.facade.query.namespace.BondDialogueQuoteOrderNamespace;
import com.xquant.platform.component.cfets.facade.query.namespace.BondMarketMakeQuoteOrderNamespace;
import com.xquant.platform.component.cfets.facade.query.namespace.BondPriceLimitQuoteOrderNamespace;
import com.xquant.platform.component.cfets.facade.query.namespace.BondRfqReplyQuoteOrderNamespace;
import com.xquant.platform.component.cfets.facade.query.namespace.BondRfqReqQuoteOrderNamespace;
import com.xquant.platform.component.cfets.facade.query.namespace.PledgeRepoDialogueQuoteOrderNamespace;
import com.xquant.platform.component.cfets.facade.query.namespace.XswapQuoteOrderNamespace;
import com.xquant.platform.component.cfets.facade.query.vo.bond.BondClickDealQuoteOrderVO;
import com.xquant.platform.component.cfets.facade.query.vo.bond.BondDialogueQuoteOrderVO;
import com.xquant.platform.component.cfets.facade.query.vo.bond.BondMarketMakeQuoteOrderVO;
import com.xquant.platform.component.cfets.facade.query.vo.bond.BondPriceLimitQuoteOrderVO;
import com.xquant.platform.component.cfets.facade.query.vo.bond.BondRfqReplyQuoteOrderVO;
import com.xquant.platform.component.cfets.facade.query.vo.bond.BondRfqReqQuoteOrderVO;
import com.xquant.platform.component.cfets.facade.query.vo.pledgerepo.PledgeRepoDialogueQuoteOrderVO;
import com.xquant.platform.component.cfets.facade.query.vo.xswap.XSwapQuoteOrderVO;
import com.xquant.platform.component.compute.api.ComputeService;
import com.xquant.platform.component.darren.util.QuoteCommonPropertiesUtil;
import com.xquant.platform.component.itf.cfets.common.api.dto.quote.QuoteOrder;
import com.xquant.platform.component.itf.cfets.genericquery.api.dto.IQueryObjectWithPk;
import com.xquant.platform.component.itf.cfets.genericquery.api.service.GenericQueryService;

@Component
public class CommonController {

	@Autowired
	private GenericQueryService genericQueryService;

	@Autowired
	protected ComputeService computeService;

	/**
	 * 根据报价编号进行对话报价主键查询
	 * 
	 * @param quoteId
	 * @return
	 */
	public BondDialogueQuoteOrderVO queryBondDlgOrderByQuoteId(String quoteId) {
		IQueryObjectWithPk<BondDialogueQuoteOrderVO> createQueryWithPk = genericQueryService
				.createQueryWithPk(BondDialogueQuoteOrderNamespace.class);
		createQueryWithPk.addCondi(BondDialogueQuoteOrderNamespace.QUOTE_ID.eq(quoteId));
		return genericQueryService.getByPk(createQueryWithPk);
	}

	/**
	 * 根据报价编号进行请求报价主键查询
	 * 
	 * @param quoteId
	 * @return
	 */
	public BondRfqReqQuoteOrderVO queryBondRfqReqOrderByQuoteId(String quoteId) {	
		IQueryObjectWithPk<BondRfqReqQuoteOrderVO> createQueryWithPk = genericQueryService
				.createQueryWithPk(BondRfqReqQuoteOrderNamespace.class);
		createQueryWithPk.addCondi(BondRfqReqQuoteOrderNamespace.QUOTE_ID.eq(quoteId));
		return genericQueryService.getByPk(createQueryWithPk);
	}

	/**
	 * 根据报价编号进行做市报价主键查询
	 * 
	 * @param quoteId
	 * @return
	 */
	public BondMarketMakeQuoteOrderVO queryMarketMakerOrderByQuoteId(String quoteId) {
		IQueryObjectWithPk<BondMarketMakeQuoteOrderVO> createQueryWithPk = genericQueryService
				.createQueryWithPk(BondMarketMakeQuoteOrderNamespace.class);
		createQueryWithPk.addCondi(BondMarketMakeQuoteOrderNamespace.QUOTE_ID.eq(quoteId));
		return genericQueryService.getByPk(createQueryWithPk);
	}

	/**
	 * 根据报价编号进行限价报价主键查询
	 * 
	 * @param quoteId
	 * @return
	 */
	public BondPriceLimitQuoteOrderVO queryPricelimitOrderByQuoteId(String quoteId) {
		IQueryObjectWithPk<BondPriceLimitQuoteOrderVO> createQueryWithPk = genericQueryService
				.createQueryWithPk(BondPriceLimitQuoteOrderNamespace.class);
		createQueryWithPk.addCondi(BondPriceLimitQuoteOrderNamespace.QUOTE_ID.eq(quoteId));
		return genericQueryService.getByPk(createQueryWithPk);
	}

	/**
	 * 根据报价编号进行点击成交报价主键查询
	 * 
	 * @param quoteId
	 * @return
	 */
	public BondClickDealQuoteOrderVO queryClickDealOrderByQuoteId(String quoteId) {
		IQueryObjectWithPk<BondClickDealQuoteOrderVO> createQueryWithPk = genericQueryService
				.createQueryWithPk(BondClickDealQuoteOrderNamespace.class);
		createQueryWithPk.addCondi(BondClickDealQuoteOrderNamespace.QUOTE_ID.eq(quoteId));
		return genericQueryService.getByPk(createQueryWithPk);
	}

	/**
	 * 根据报价编号进行请求回复报价主键查询
	 * 
	 * @param quoteId
	 * @return
	 */
	public BondRfqReplyQuoteOrderVO queryBondReplyOrderByQuoteId(String quoteId) {
		IQueryObjectWithPk<BondRfqReplyQuoteOrderVO> createQueryWithPk = genericQueryService
				.createQueryWithPk(BondRfqReplyQuoteOrderNamespace.class);
		createQueryWithPk.addCondi(BondRfqReplyQuoteOrderNamespace.QUOTE_ID.eq(quoteId));
		return genericQueryService.getByPk(createQueryWithPk);
	}

	/**
	 * 进行质押式回购报价的主键查询
	 * 
	 * @param quoteId
	 * @return
	 */
	public PledgeRepoDialogueQuoteOrderVO queryPledgeOrderByQuoteId(String quoteId) {
		IQueryObjectWithPk<PledgeRepoDialogueQuoteOrderVO> createQueryWithPk = genericQueryService
				.createQueryWithPk(PledgeRepoDialogueQuoteOrderNamespace.class);
		createQueryWithPk.addCondi(PledgeRepoDialogueQuoteOrderNamespace.QUOTE_ID.eq(quoteId));
		return genericQueryService.getByPk(createQueryWithPk);
	}
	
	/**
	 * 进行xswap报价的主键查询
	 * @param quoteId
	 * @return
	 */
	public XSwapQuoteOrderVO queryXswapQuoteOrderByQuoteId(String quoteId) {
		IQueryObjectWithPk<XSwapQuoteOrderVO> queryObjectWithPk = genericQueryService.createQueryWithPk(XswapQuoteOrderNamespace.class);
		queryObjectWithPk.addCondi(XswapQuoteOrderNamespace.QUOTE_ID.eq(quoteId));
		return genericQueryService.getByPk(queryObjectWithPk);
	}
	
	/**
	 * 添加本方机构以及交易员信息
	 * @param quoteOrder
	 */
	public void setSelfAccount(QuoteOrder quoteOrder) {
		QuoteCommonPropertiesUtil.setRequestSideInfo(quoteOrder);
	}


	/**
	 * 通过response向前台页面返回对象信息
	 * 
	 * @param response
	 * @param obj
	 *            返回的对象
	 */
	public void print(HttpServletResponse response, Object obj) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			response.getWriter().print(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
