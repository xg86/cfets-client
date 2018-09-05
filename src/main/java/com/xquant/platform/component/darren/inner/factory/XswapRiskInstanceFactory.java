/**
 * 
 */
package com.xquant.platform.component.darren.inner.factory;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.xquant.platform.component.darren.util.MockGenerateUtil;
import com.xquant.platform.component.itf.cfets.xswap.api.dto.XswapRiskLimitQueryRequest;
import com.xquant.platform.component.itf.cfets.xswap.api.dto.XswapRiskLimitRequestCounterParty;
import com.xquant.platform.component.itf.cfets.xswap.api.dto.XswapRiskLimitSetRequest;
import com.xquant.platform.component.itf.cfets.xswap.api.dto.XswapRiskRatioInstrument;
import com.xquant.platform.component.itf.cfets.xswap.api.dto.XswapRiskRatioQueryRequest;
import com.xquant.platform.component.itf.cfets.xswap.api.dto.XswapRiskRatioSetRequest;
import com.xquant.platform.component.itf.cfets.xswap.api.enums.XSwapClearingMethodEnum;
import com.xquant.platform.component.itf.cfets.xswap.api.enums.XSwapMarketIndicatorEnum;
import com.xquant.platform.component.itf.cfets.xswap.api.enums.XSwapRiskLimitRelationEnum;
import com.xquant.platform.component.itf.cfets.xswap.api.enums.XSwapRiskLimitUpdateMethoddEnum;
import com.xquant.platform.component.itf.cfets.xswap.api.enums.XSwapSecurityTypeEnum;

/**
 * Copyright © 2018 xQuant Info. Tech Ltd. All rights reserved.
 * 
 * @Package: com.xquant.platform.component.darren.inner.factory
 * @author: guanglai.zhou
 * @date: 2018-08-28 11:38:19
 */
public class XswapRiskInstanceFactory {

	/**
	 * 获取授信设置参数
	 * 
	 * @return
	 */
	public static XswapRiskLimitSetRequest getRiskLimitRelationSetRequest() {
		XswapRiskLimitSetRequest request = new XswapRiskLimitSetRequest();
		request.setClientReqID(MockGenerateUtil.getNextClientReqID());
		request.setParty("179001533010000401021");
		request.setTransactTime(new SimpleDateFormat("yyyyMMdd-HH:mm:ss").format(new Date()));
		request.setTrader("hthtcapi");
		request.setRiskLimitID("-");
		setRiskLimitWithRelation(request);
		return request;
	}
	
	/**
	 * 获取授信设置参数
	 * 
	 * @return
	 */
	public static XswapRiskLimitSetRequest getRiskLimitUnRelationSetRequest() {
		XswapRiskLimitSetRequest request = new XswapRiskLimitSetRequest();
		request.setClientReqID(MockGenerateUtil.getNextClientReqID());
		request.setParty("179001533010000401021");
		request.setTransactTime(new SimpleDateFormat("yyyyMMdd-HH:mm:ss").format(new Date()));
		request.setTrader("hthtcapi");
		request.setRiskLimitID("-");
		setRiskLimitWithUnRelation(request);
		return request;
	}

	/**
	 * 获取授信设置参数
	 * 
	 * @return
	 */
	public static XswapRiskLimitSetRequest getRiskLimitAmountSetRequest() {
		XswapRiskLimitSetRequest request = new XswapRiskLimitSetRequest();
		request.setClientReqID(MockGenerateUtil.getNextClientReqID());
		request.setParty("179001533010000401021");
		request.setTransactTime(new SimpleDateFormat("yyyyMMdd-HH:mm:ss").format(new Date()));
		request.setTrader("hthtcapi");
		request.setRiskLimitID("-");
		setRiskLimitWithAmount(request);
		return request;
	}

	/**
	 * 获取授信查询接口
	 * 
	 * @return
	 */
	public static XswapRiskLimitQueryRequest getRiskLimitQueryRequest() {
		XswapRiskLimitQueryRequest request = new XswapRiskLimitQueryRequest();
		request.setClientReqID(MockGenerateUtil.getNextClientReqID());
		request.setParty("179001533010000401021");
		request.setTransactTime(new SimpleDateFormat("yyyyMMdd-HH:mm:ss").format(new Date()));
		request.setTrader("hthtcapi");
		request.setRiskLimitRequestType("8");
		request.setMarketIndicator("0");
		request.setMarketID("X-Swap");
		request.setInstrumentScopeSecurityType("SIRS");
		request.setCountparty("891012844010011610011");
		return request;
	}

	/**
	 * 获取授信额度设置接口参数
	 * 
	 * @return
	 */
	public static XswapRiskRatioSetRequest getRiskRatioSetRequest() {
		XswapRiskRatioSetRequest request = new XswapRiskRatioSetRequest();
		request.setClientReqID(MockGenerateUtil.getNextClientReqID());
		request.setParty("179001533010000401021");
		request.setTransactTime(new SimpleDateFormat("yyyyMMdd-HH:mm:ss").format(new Date()));
		request.setTrader("hthtcapi");
		request.setRiskLimitID("-");
		List<XswapRiskRatioInstrument> xswapRiskRatioInstruments = new ArrayList<XswapRiskRatioInstrument>();
		XswapRiskRatioInstrument instrument = new XswapRiskRatioInstrument();
		 instrument.setClearingMethod(XSwapClearingMethodEnum.BILATERAL);
		 instrument.setInstrumentScopeSecurityType(XSwapSecurityTypeEnum.SIRS);
		 instrument.setMarketIndicator(XSwapMarketIndicatorEnum.SIRS);
		 instrument.setSecurityID("SS1W3M_1903");
		 instrument.setRiskInstrumentMultiplier(new BigDecimal("1.8888"));
		 xswapRiskRatioInstruments.add(instrument);
		 instrument = new XswapRiskRatioInstrument();
		 instrument.setClearingMethod(XSwapClearingMethodEnum.BILATERAL);
		 instrument.setInstrumentScopeSecurityType(XSwapSecurityTypeEnum.SIRS);
		 instrument.setMarketIndicator(XSwapMarketIndicatorEnum.SIRS);
		 instrument.setSecurityID("SR073M_1809");
		 instrument.setRiskInstrumentMultiplier(new BigDecimal("1.8888"));
		 xswapRiskRatioInstruments.add(instrument);
		 instrument = new XswapRiskRatioInstrument();
		 instrument.setClearingMethod(XSwapClearingMethodEnum.BILATERAL);
		 instrument.setInstrumentScopeSecurityType(XSwapSecurityTypeEnum.SIRS);
		 instrument.setMarketIndicator(XSwapMarketIndicatorEnum.SIRS);
		 instrument.setSecurityID("SR073M_1810");
		 instrument.setRiskInstrumentMultiplier(new BigDecimal("1.8888"));
		 xswapRiskRatioInstruments.add(instrument);
		 instrument = new XswapRiskRatioInstrument();
		 instrument.setClearingMethod(XSwapClearingMethodEnum.BILATERAL);
		 instrument.setInstrumentScopeSecurityType(XSwapSecurityTypeEnum.SIRS);
		 instrument.setMarketIndicator(XSwapMarketIndicatorEnum.SIRS);
		 instrument.setSecurityID("SR073M_1811");
		 instrument.setRiskInstrumentMultiplier(new BigDecimal("1.8888"));
		 xswapRiskRatioInstruments.add(instrument);
		 instrument = new XswapRiskRatioInstrument();
		 instrument.setClearingMethod(XSwapClearingMethodEnum.BILATERAL);
		 instrument.setInstrumentScopeSecurityType(XSwapSecurityTypeEnum.SIRS);
		 instrument.setMarketIndicator(XSwapMarketIndicatorEnum.SIRS);
		 instrument.setSecurityID("SR073M_1812");
		 instrument.setRiskInstrumentMultiplier(new BigDecimal("1.8888"));
		 xswapRiskRatioInstruments.add(instrument);
		request.setXswapRiskRatioInstruments(xswapRiskRatioInstruments);
		return request;
	}

	/**
	 * 获取授信额度查询接口参数
	 * 
	 * @return
	 */
	public static XswapRiskRatioQueryRequest getRiskRatioQueryRequest() {
		XswapRiskRatioQueryRequest request = new XswapRiskRatioQueryRequest();
		request.setClientReqID(MockGenerateUtil.getNextClientReqID());
		request.setParty("179001533010000401021");
		request.setTransactTime(new SimpleDateFormat("yyyyMMdd-HH:mm:ss").format(new Date()));
		request.setTrader("hthtcapi");
		request.setRiskRatioRequestType("7");
		request.setClearingMethod(XSwapClearingMethodEnum.BILATERAL);
		request.setInstrumentScopeSecurityType(XSwapSecurityTypeEnum.SIRS);
		request.setMarketIndicator(XSwapMarketIndicatorEnum.SIRS);
		return request;
	}

	/**
	 * 设置额度设置
	 * 
	 * @param setRequest
	 */
	private static void setRiskLimitWithAmount(XswapRiskLimitSetRequest setRequest) {
		// 进行额度授信
		setRequest.setRiskLimitUpdateMethod(XSwapRiskLimitUpdateMethoddEnum.VIA_AMOUNT);
		List<XswapRiskLimitRequestCounterParty> riskLimitSetCountPartyList = new ArrayList<XswapRiskLimitRequestCounterParty>();
		XswapRiskLimitRequestCounterParty counterPartyInfo = new XswapRiskLimitRequestCounterParty();
		counterPartyInfo.setCountParty("891012844010011610011");
		counterPartyInfo.setRiskRelationShip(XSwapRiskLimitRelationEnum.MAKE_RELATION);
		counterPartyInfo.setMarketID("X-Swap");
		counterPartyInfo.setMarketIndicator("0");
		counterPartyInfo.setRiskLimitAmount("100000000");
		counterPartyInfo.setRiskTerm("Y2");
		riskLimitSetCountPartyList.add(counterPartyInfo);
		counterPartyInfo = new XswapRiskLimitRequestCounterParty();
		counterPartyInfo.setCountParty("103005212000000205011");
		counterPartyInfo.setRiskRelationShip(XSwapRiskLimitRelationEnum.MAKE_RELATION);
		counterPartyInfo.setMarketID("X-Swap");
		counterPartyInfo.setMarketIndicator("0");
		counterPartyInfo.setRiskLimitAmount("100000000");
		counterPartyInfo.setRiskTerm("Y2");
		riskLimitSetCountPartyList.add(counterPartyInfo);
		setRequest.setRiskLimitSetCountPartyList(riskLimitSetCountPartyList);
	}

	/**
	 * 进行关系设置
	 * 
	 * @param setRequest
	 */
	private static void setRiskLimitWithRelation(XswapRiskLimitSetRequest setRequest) {
		// 进行额度授信
		setRequest.setRiskLimitUpdateMethod(XSwapRiskLimitUpdateMethoddEnum.VIA_RELATION);
		List<XswapRiskLimitRequestCounterParty> riskLimitSetCountPartyList = new ArrayList<XswapRiskLimitRequestCounterParty>();
		XswapRiskLimitRequestCounterParty counterPartyInfo = new XswapRiskLimitRequestCounterParty();
		counterPartyInfo.setCountParty("891012844010011610011");
		counterPartyInfo.setMarketID("X-Swap");
		counterPartyInfo.setMarketIndicator("0");
		counterPartyInfo.setRiskRelationShip(XSwapRiskLimitRelationEnum.MAKE_RELATION);
		riskLimitSetCountPartyList.add(counterPartyInfo);
		counterPartyInfo = new XswapRiskLimitRequestCounterParty();
		counterPartyInfo.setCountParty("103005212000000205011");
		counterPartyInfo.setMarketID("X-Swap");
		counterPartyInfo.setMarketIndicator("0");
		counterPartyInfo.setRiskRelationShip(XSwapRiskLimitRelationEnum.MAKE_RELATION);
		riskLimitSetCountPartyList.add(counterPartyInfo);
		setRequest.setRiskLimitSetCountPartyList(riskLimitSetCountPartyList);
	}
	
	/**
	 * 进行关系设置
	 * 
	 * @param setRequest
	 */
	private static void setRiskLimitWithUnRelation(XswapRiskLimitSetRequest setRequest) {
		// 进行额度授信
		setRequest.setRiskLimitUpdateMethod(XSwapRiskLimitUpdateMethoddEnum.VIA_RELATION);
		List<XswapRiskLimitRequestCounterParty> riskLimitSetCountPartyList = new ArrayList<XswapRiskLimitRequestCounterParty>();
		XswapRiskLimitRequestCounterParty counterPartyInfo = new XswapRiskLimitRequestCounterParty();
		counterPartyInfo.setCountParty("891012844010011610011");
		counterPartyInfo.setMarketID("X-Swap");
		counterPartyInfo.setMarketIndicator("0");
		counterPartyInfo.setRiskRelationShip(XSwapRiskLimitRelationEnum.END_RELATION);
		riskLimitSetCountPartyList.add(counterPartyInfo);
		counterPartyInfo = new XswapRiskLimitRequestCounterParty();
		counterPartyInfo.setCountParty("103005212000000205011");
		counterPartyInfo.setMarketID("X-Swap");
		counterPartyInfo.setMarketIndicator("0");
		counterPartyInfo.setRiskRelationShip(XSwapRiskLimitRelationEnum.END_RELATION);
		riskLimitSetCountPartyList.add(counterPartyInfo);
		setRequest.setRiskLimitSetCountPartyList(riskLimitSetCountPartyList);
	}
}
