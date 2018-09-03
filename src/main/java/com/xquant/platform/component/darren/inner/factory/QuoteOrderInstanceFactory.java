package com.xquant.platform.component.darren.inner.factory;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xquant.platform.component.commons.ATypeDefines;
import com.xquant.platform.component.commons.ExeMarketEnum;
import com.xquant.platform.component.commons.MarketTypeEnum;
import com.xquant.platform.component.commons.SetTypeEnum;
import com.xquant.platform.component.commons.TradeTypeEnum;
import com.xquant.platform.component.compute.api.ComputeService;
import com.xquant.platform.component.darren.util.DatePatternUtil;
import com.xquant.platform.component.darren.util.DateTimeUtil;
import com.xquant.platform.component.darren.util.QuoteCommonPropertiesUtil;
import com.xquant.platform.component.itf.cfets.api.dto.quote.bond.BondClickDealQuoteOrder;
import com.xquant.platform.component.itf.cfets.api.dto.quote.bond.BondDialogueQuoteOrder;
import com.xquant.platform.component.itf.cfets.api.dto.quote.bond.BondMarketMakeQuoteOrder;
import com.xquant.platform.component.itf.cfets.api.dto.quote.bond.BondPriceLimitQuoteOrder;
import com.xquant.platform.component.itf.cfets.api.dto.quote.bond.BondRfqReplyQuoteOrder;
import com.xquant.platform.component.itf.cfets.api.dto.quote.bond.BondRfqReqQuoteOrder;
import com.xquant.platform.component.itf.cfets.api.dto.quote.pledgerepo.PledgeRepoDialogueQuoteOrder;
import com.xquant.platform.component.itf.cfets.api.enums.quote.AnonymousIndicatorEnum;
import com.xquant.platform.component.itf.cfets.api.enums.quote.QuoteTypeEnum;
import com.xquant.platform.component.itf.cfets.api.enums.quote.SplitIndicatorEnum;
import com.xquant.platform.component.itf.cfets.common.entity.CfetsInstitution;
import com.xquant.platform.component.itf.cfets.xswap.api.dto.XSwapQuoteOrder;
import com.xquant.platform.component.itf.cfets.xswap.api.enums.XSwapClearingMethodEnum;
import com.xquant.platform.component.itf.cfets.xswap.api.enums.XSwapMarketIndicatorEnum;
import com.xquant.platform.component.itf.cfets.xswap.api.enums.XSwapOrdTypeEnum;
import com.xquant.platform.component.itf.cfets.xswap.api.enums.XSwapOrderCancelTypeEnum;
import com.xquant.platform.component.itf.cfets.xswap.api.enums.XSwapSecurityTypeEnum;
import com.xquant.platform.component.util.DateUtils;

@Component
public class QuoteOrderInstanceFactory {

	@Autowired
	private ComputeService computeService;

	/**
	 * 增加报价时的净价
	 */
	public static final String NET_PRICE_ADD = "99.9";

	/**
	 * 报价量
	 */
	public static final BigDecimal QUOTE_VOLUME = new BigDecimal("100000");

	/**
	 * 用于获取对话报价新增报价的实例
	 * 
	 * @return
	 */
	public BondDialogueQuoteOrder getBondDialogueQuoteOrder4Add() {
		BondDialogueQuoteOrder quoteOrder = new BondDialogueQuoteOrder();

		// 设置共有信息
		QuoteCommonPropertiesUtil.setCommonProperties(computeService, quoteOrder);
		quoteOrder.setQuoteType(QuoteTypeEnum.DLG);
		// 新增报价需要设置发送方和接收方信息
		QuoteCommonPropertiesUtil.setRequestSideInfo(quoteOrder);
		QuoteCommonPropertiesUtil.setResponseSideInfo(quoteOrder);

		return quoteOrder;
	}

	/**
	 * 用于获取点击成交新增报价的实例
	 * 
	 * @return
	 */
	public BondClickDealQuoteOrder getBondClickDealQuoteOrder4Add() {
		BondClickDealQuoteOrder quoteOrder = new BondClickDealQuoteOrder();
		// 设置共有信息
		QuoteCommonPropertiesUtil.setCommonProperties(computeService, quoteOrder);
		// 匿名标识
		quoteOrder.setAnonymousIndicator(AnonymousIndicatorEnum.NO);
		// 最大显示券面总额
		quoteOrder.setMaxFloor(new BigDecimal(1000000000));
		// 最小变动单位
		quoteOrder.setMinTickSize(new BigDecimal(10000000));
		quoteOrder.setQuoteType(QuoteTypeEnum.CLICK_DEAL);
		QuoteCommonPropertiesUtil.setRequestSideInfo(quoteOrder);
		quoteOrder.setCountryPartyId(-1L);

		return quoteOrder;
	}

	/**
	 * 用于获取限价报价新增报价的实例
	 * 
	 * @return
	 */
	public BondPriceLimitQuoteOrder getBondPriceLimitQuoteOrder4Add() {
		BondPriceLimitQuoteOrder quoteOrder = new BondPriceLimitQuoteOrder();
		// 拆分标识
		quoteOrder.setSplitIndicator(SplitIndicatorEnum.YES);
		// 最小变动单位
		quoteOrder.setMinTickSize(new BigDecimal(10000000));
		// 交易类型
		quoteOrder.setQuoteType(QuoteTypeEnum.PRICE_LIMIT);
		// 设置共有信息
		QuoteCommonPropertiesUtil.setCommonProperties(computeService, quoteOrder);
		// 新增报价需要设置发送方和接收方信息
		QuoteCommonPropertiesUtil.setRequestSideInfo(quoteOrder);
		quoteOrder.setCountryPartyId(-1L);
		return quoteOrder;
	}

	/**
	 * 用于获取做市报价新增报价的实例
	 * 
	 * @return
	 */
	public BondMarketMakeQuoteOrder getBondMarketMakeQuoteOrder4Add() {
		BondMarketMakeQuoteOrder quoteOrder = new BondMarketMakeQuoteOrder();
		// quoteOrder.setClrOrdId(clrOrdId);
		// 匿名标识
		quoteOrder.setAnonymousIndicator(AnonymousIndicatorEnum.NO);
		// 最大显示券面总额
		quoteOrder.setMaxFloor(new BigDecimal(10000000));
		// 最小成交变动单位
		quoteOrder.setMinTickSize(new BigDecimal(10000000));
		quoteOrder.setQuoteType(QuoteTypeEnum.MARKET_MAKE);
//		quoteOrder.setICode("020205");
		quoteOrder.setICode("1080023");
		quoteOrder.setIName("02国开05");
		quoteOrder.setWaitUntilTime(DateTimeUtil.timeAfterMinuteWithFormat(28));
		quoteOrder.setAType(ATypeDefines.A_TYPE_CODE_SPT_BD);
		quoteOrder.setMType(MarketTypeEnum.X_CNBD);
		quoteOrder.setExeMarket(ExeMarketEnum.X_CNBD_IN);
		quoteOrder.setOrdDate(DateUtils.getBussinessDate());

		// 设置一个买方
		BondClickDealQuoteOrder buyPart = new BondClickDealQuoteOrder();
		QuoteCommonPropertiesUtil.setCommonProperties(computeService, buyPart);
		buyPart.setNetPrice(new BigDecimal("99.0"));
		// buyPart.setICode("020205");
		// buyPart.setMaxFloor(new BigDecimal(10000000));
		quoteOrder.setBuyPart(buyPart);
		BondClickDealQuoteOrder sellPart = new BondClickDealQuoteOrder();
		QuoteCommonPropertiesUtil.setCommonProperties(computeService, sellPart);
		// sellPart.setICode("020205");
		// sellPart.setMaxFloor(new BigDecimal(10000000));
		sellPart.setNetPrice(new BigDecimal("101.0"));
		quoteOrder.setSellPart(sellPart);
		QuoteCommonPropertiesUtil.setRequestSideInfo(quoteOrder);
		quoteOrder.setCountryPartyId(-1L);
		return quoteOrder;
	}

	/**
	 * 用于获取请求报价新增报价的实例 ,需要额外填充的消息示例如下：
	 * <li>// 报价有效时间 只能为半个小时</li>
	 * <li>quoteOrder.setWaitUntilTime("20171222-12:33:56.000");</li>
	 * 
	 * @return
	 */
	public BondRfqReqQuoteOrder getBondRfqReqQuoteOrder4Add() {
		BondRfqReqQuoteOrder quoteOrder = new BondRfqReqQuoteOrder();

		quoteOrder.setQuoteType(QuoteTypeEnum.RFQ_REQUEST);
		QuoteCommonPropertiesUtil.setCommonProperties(computeService, quoteOrder);
		// 请求报价只含量 不含价格
		quoteOrder.setNetPrice(null);
		quoteOrder.setFullPrice(null);
		quoteOrder.setSettleAmount(null);
		quoteOrder.setYtm(null);
		// 设置发送方信息
		QuoteCommonPropertiesUtil.setRequestSideInfo(quoteOrder);
		List<CfetsInstitution> partys = new ArrayList<CfetsInstitution>();
		CfetsInstitution inst1 = new CfetsInstitution();
		inst1.setCfetsOrgCode("891012844010011610011");
		inst1.setiName("广发基金");
		partys.add(inst1);
		CfetsInstitution inst2 = new CfetsInstitution();
		inst2.setCfetsOrgCode("103005212000000205011");
		inst2.setiName("渤海证券");
		partys.add(inst2);
		CfetsInstitution inst3 = new CfetsInstitution();
		inst3.setCfetsOrgCode("119000043010000104001");
		inst3.setiName("长沙银行");
		partys.add(inst3);
		CfetsInstitution inst4 = new CfetsInstitution();
		inst4.setCfetsOrgCode("109010022010000205011");
		inst4.setiName("东北证券");
		partys.add(inst4);
		CfetsInstitution inst5 = new CfetsInstitution();
		inst5.setCfetsOrgCode("102010431000000205011");
		inst5.setiName("东方证券");
		partys.add(inst5);
		quoteOrder.setPartys(partys);
		return quoteOrder;
	}

	/**
	 * 用于获取请求回复报价新增报价的实例 根据请求报价生成 只改变交易方向
	 * 
	 * @return
	 */
	public BondRfqReplyQuoteOrder getBondRfqReplyQuoteOrder4Add(BondRfqReqQuoteOrder bondRfqReqQuoteOrder) {
		BondRfqReplyQuoteOrder quoteOrder = new BondRfqReplyQuoteOrder();
		try {
			BeanUtils.copyProperties(quoteOrder, bondRfqReqQuoteOrder);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		quoteOrder.setQuoteType(QuoteTypeEnum.REQ_REQUEST_REPLY);
		quoteOrder.setQuoteReqID(bondRfqReqQuoteOrder.getQuoteId());
		quoteOrder.setQuoteId(null);
		quoteOrder.setTransactTime(null);
		quoteOrder.setOrdId(null);
		quoteOrder.setClordidClientId(null);
		quoteOrder.setClrOrdId(null);
		quoteOrder.setNetPrice(new BigDecimal(QuoteOrderInstanceFactory.NET_PRICE_ADD));
		quoteOrder.setSettleDate(DateFormatUtils.ISO_DATE_FORMAT.format(new Date()));
		QuoteCommonPropertiesUtil.fillWithCompute(computeService, quoteOrder);
		QuoteCommonPropertiesUtil.setRequestSideInfo(quoteOrder);
		quoteOrder.setTraderCp(bondRfqReqQuoteOrder.getTraderCp());
		quoteOrder.setTraderCpCfetsId(bondRfqReqQuoteOrder.getTraderCpCfetsId());
		quoteOrder.setCountryPartyCfetsId(bondRfqReqQuoteOrder.getCountryPartyCfetsId());
		quoteOrder.setCountryPartyId(bondRfqReqQuoteOrder.getCountryPartyId());
		QuoteCommonPropertiesUtil.setResponseSideInfo(quoteOrder);
		return quoteOrder;
	}

	/**
	 * 用于获取质押式回购新增报价的实例
	 * 
	 * @return
	 */
	public PledgeRepoDialogueQuoteOrder getPledgeRepoDialogueQuoteOrder4Add() {
		PledgeRepoDialogueQuoteOrder quoteOrder = new PledgeRepoDialogueQuoteOrder();

		quoteOrder.setQuoteType(QuoteTypeEnum.DLG);
		QuoteCommonPropertiesUtil.setCommonProperties(computeService, quoteOrder);
		// 新增报价需要设置发送方和接收方信息
		QuoteCommonPropertiesUtil.setRequestSideInfo(quoteOrder);
		QuoteCommonPropertiesUtil.setResponseSideInfo(quoteOrder);
		return quoteOrder;
	}

	/**
	 * 利率互换新增实体类 0 利率互换 1 标准利率互换
	 * 
	 * @return
	 */
	public XSwapQuoteOrder getXswapQuoteOrder4Add(int irsType) {
		XSwapQuoteOrder quoteOrder = new XSwapQuoteOrder();

		// 交易日期
		quoteOrder.setOrdDate(DateUtils.getBussinessDate());
		// 结算日期 固定为交易日
		quoteOrder.setSettleDate(DateUtils.getBussinessDate());
		// 结算方式 固定为DVP
		quoteOrder.setSetType(SetTypeEnum.DVP);

		// 交易时间
		quoteOrder.setOrdTime(DatePatternUtil.ISO_TIME_NO_T_FORMAT.format(new Date()));
		// 报价类别 可选择点击成交（做市方）和限价报价（非做市方）
		// quoteOrder.setQuoteType(XSwapOrdTypeEnum.CLICK_DEAL);
		quoteOrder.setQuoteType(XSwapOrdTypeEnum.PRICE_LIMIT);
		// 报价有效时间
		quoteOrder.setWaitUntilTime(DateTimeUtil.getValidateTime());

		// 债券编码 合约品种 以下参数可以从TIRSWAPS表中查询
//		 quoteOrder.setICode("SS1W3M_1903");
		quoteOrder.setICode("FR007_3M");
		// aType 利率互换 SWP_IR
		quoteOrder.setAType(ATypeDefines.A_TYPE_CODE_SWP_IR);
		// 市场类型 X_CNBD
		quoteOrder.setMType(MarketTypeEnum.X_CNBD);
		// 债券名称
		// quoteOrder.setIName("3个月标准Shibor1W利率互换");
		quoteOrder.setIName("FR007");
		// 起息日
		quoteOrder.setStartDate("2018-12-19");
		// 到期日
		quoteOrder.setMtrDate("2019-03-20");
		// 产品分类
		quoteOrder.setpClass("SHIBOR-1W");

		// 执行市场 固定为银行间场内
		quoteOrder.setExeMarket(ExeMarketEnum.X_CNBD_IN);

		TradeTypeEnum sideRate = new Random().nextDouble() < 0.5 ? TradeTypeEnum.TRADE_TYPE_XSWAP_PAY_FIX
				: TradeTypeEnum.TRADE_TYPE_XSWAP_PAY_FLOAT;

		// 交易类型 支付固定为买 支付浮动为卖
		quoteOrder.setTrdType(sideRate);

		// 固定端利率
//		quoteOrder.setFixedCoupon(BigDecimalUtil.randDeciWithFixedScale(2));
		quoteOrder.setFixedCoupon(new BigDecimal("2.1258").divide(new BigDecimal("100")));
		// 报价量
		quoteOrder.setVolume(new BigDecimal("100000000"));

		Random rand = new Random();
		// 不支持标准债券远期
		int randInt = rand.nextInt(2);

		randInt = irsType;

		switch (randInt) {
		// 利率互换时清算类型不传
		case 0:
			// 市场类型 参照XSwapMarketIndicatorEnum
			quoteOrder.setMarketIndicator(XSwapMarketIndicatorEnum.IRS);
			// 债券类型 参照XSwapSecurityTypeEnum
			quoteOrder.setSecurityType(XSwapSecurityTypeEnum.IRS);

			break;
		// 标准利率互换时 清算类型为13
		case 1:
			// 市场类型 参照XSwapMarketIndicatorEnum
			quoteOrder.setMarketIndicator(XSwapMarketIndicatorEnum.SIRS);
			// 债券类型 参照XSwapSecurityTypeEnum
			quoteOrder.setSecurityType(XSwapSecurityTypeEnum.SIRS);
			// 清算类型 参照
			quoteOrder.setClearingMethod(XSwapClearingMethodEnum.BILATERAL);

			break;
		// 标准债券远期时 清算类型为6 不传利率 传价格
		case 2:
			// 市场类型 参照XSwapMarketIndicatorEnum
			quoteOrder.setMarketIndicator(XSwapMarketIndicatorEnum.SIRS);
			// 债券类型 参照XSwapSecurityTypeEnum
			quoteOrder.setSecurityType(XSwapSecurityTypeEnum.SIRS);
			// 清算类型 参照
			quoteOrder.setClearingMethod(XSwapClearingMethodEnum.NETCLEARING);
			// 固定端利率
			quoteOrder.setFixedCoupon(new BigDecimal(""));
			// 净价
			quoteOrder.setPrice(new BigDecimal("98.6000"));
			// 报价量
			quoteOrder.setVolume(new BigDecimal("1000000"));

			break;
		default:
			break;
		}

		QuoteCommonPropertiesUtil.setRequestSideInfo4Xswap(quoteOrder);

		return quoteOrder;
	}

	/**
	 * 利率互换撤销实体类
	 * 
	 * @param origOperateSeqNum
	 *            原订单参考编号
	 * @return
	 */
	public XSwapQuoteOrder getXswapQuoteOrder4Cancel(int irsType) {
		XSwapQuoteOrder quoteOrder = new XSwapQuoteOrder();

		// 交易日期
		quoteOrder.setOrdDate(DateUtils.getBussinessDate());
		// 交易时间
		quoteOrder.setOrdTime(DatePatternUtil.ISO_TIME_NO_T_FORMAT.format(new Date()));
		// 撤销标识
		quoteOrder.setOrderCancelType(XSwapOrderCancelTypeEnum.SINGLE_CANCELED);
		// 撤销的订单编号 全部撤销时可以不传
		quoteOrder.setOrdId(null);
		Random rand = new Random();
		// 不支持标准债券远期
		int randInt = rand.nextInt(2);

		randInt = irsType;

		switch (randInt) {
		// 利率互换时清算类型不传
		case 0:
			// 市场类型 参照XSwapMarketIndicatorEnum
			quoteOrder.setMarketIndicator(XSwapMarketIndicatorEnum.IRS);
			// 债券类型 参照XSwapSecurityTypeEnum
			quoteOrder.setSecurityType(XSwapSecurityTypeEnum.IRS);

			break;
		// 标准利率互换时 清算类型为13
		case 1:
			// 市场类型 参照XSwapMarketIndicatorEnum
			quoteOrder.setMarketIndicator(XSwapMarketIndicatorEnum.SIRS);
			// 债券类型 参照XSwapSecurityTypeEnum
			quoteOrder.setSecurityType(XSwapSecurityTypeEnum.SIRS);
			// 清算类型 参照
			quoteOrder.setClearingMethod(XSwapClearingMethodEnum.BILATERAL);

			break;
		// 标准债券远期时 清算类型为6 不传利率 传价格
		case 2:
			// 市场类型 参照XSwapMarketIndicatorEnum
			quoteOrder.setMarketIndicator(XSwapMarketIndicatorEnum.SIRS);
			// 债券类型 参照XSwapSecurityTypeEnum
			quoteOrder.setSecurityType(XSwapSecurityTypeEnum.SIRS);
			// 清算类型 参照
			quoteOrder.setClearingMethod(XSwapClearingMethodEnum.NETCLEARING);

			break;
		default:
			break;
		}

		QuoteCommonPropertiesUtil.setRequestSideInfo4Xswap(quoteOrder);
		return quoteOrder;
	}

}
