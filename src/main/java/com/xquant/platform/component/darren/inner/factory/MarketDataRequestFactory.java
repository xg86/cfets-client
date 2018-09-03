package com.xquant.platform.component.darren.inner.factory;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.xquant.platform.component.darren.util.MockGenerateUtil;
import com.xquant.platform.component.itf.cfets.xswap.api.dto.XSwapMarketDataSubscriptionRequest;
import com.xquant.platform.component.itf.cfets.xswap.api.enums.XSwapClearingMethodEnum;
import com.xquant.platform.component.itf.cfets.xswap.api.enums.XSwapMarketDepthEnum;
import com.xquant.platform.component.itf.cfets.xswap.api.enums.XSwapMarketIndicatorEnum;
import com.xquant.platform.component.itf.cfets.xswap.api.enums.XSwapSecurityTypeEnum;
import com.xquant.platform.component.itf.cfets.xswap.api.enums.XSwapSubscriptionRequestTypeEnum;
import com.xquant.platform.component.itf.cfets.xswap.api.enums.XswapMDBookTypeEnum;

/**
 * @Project: cfetsclienta
 * @Package: com.xquant.platform.component.darren.inner.factory
 * @author: guanglai.zhou
 * @date: 2018-08-29 09:10:43
 */
public class MarketDataRequestFactory {

	/**
	 * 获取利率互换市场深度行情订阅
	 * 
	 * @return
	 */
	public static XSwapMarketDataSubscriptionRequest getDeepIRMarketDataRequest4Xswap() {
		XSwapMarketDataSubscriptionRequest subscriptionRequest = new XSwapMarketDataSubscriptionRequest();
		subscriptionRequest.setmDReqID(MockGenerateUtil.getNextMDReqID());
		subscriptionRequest.setMarketIndicator(XSwapMarketIndicatorEnum.IRS);
		subscriptionRequest.setSecurityType(XSwapSecurityTypeEnum.IRS);
		subscriptionRequest.setClearingMethod(XSwapClearingMethodEnum.BILATERAL);
		subscriptionRequest.setSecurityId("-");
		subscriptionRequest.setSubscriptionRequestType(XSwapSubscriptionRequestTypeEnum.ADD);
		subscriptionRequest.setPartyId("179001533010000401021");
		subscriptionRequest.setmDBookType(XswapMDBookTypeEnum.DEEP_MARKET_SUB);
		subscriptionRequest.setMarketDepth(XSwapMarketDepthEnum.DEEP_MARKET_SUB);
		subscriptionRequest.setTransactTime(new SimpleDateFormat("yyyyMMdd-HH:mm:ss.sss").format(new Date()));
		subscriptionRequest.setTradeId("hzhtmapi");
		return subscriptionRequest;
	}

	/**
	 * 获取利率互换市场最优行情订阅
	 * 
	 * @return
	 */
	public static XSwapMarketDataSubscriptionRequest getBestIRMarketDataRequest4Xswap() {
		XSwapMarketDataSubscriptionRequest subscriptionRequest = new XSwapMarketDataSubscriptionRequest();
		subscriptionRequest.setmDReqID(MockGenerateUtil.getNextMDReqID());
		subscriptionRequest.setMarketIndicator(XSwapMarketIndicatorEnum.IRS);
		subscriptionRequest.setSecurityType(XSwapSecurityTypeEnum.IRS);
		subscriptionRequest.setClearingMethod(XSwapClearingMethodEnum.BILATERAL);
		subscriptionRequest.setSecurityId("-");
		subscriptionRequest.setSubscriptionRequestType(XSwapSubscriptionRequestTypeEnum.ADD);
		subscriptionRequest.setPartyId("179001533010000401021");
		subscriptionRequest.setmDBookType(XswapMDBookTypeEnum.BEST_MARKET_SUB);
		subscriptionRequest.setMarketDepth(XSwapMarketDepthEnum.BEST_MARKET_SUB);
		subscriptionRequest.setTransactTime(new SimpleDateFormat("yyyyMMdd-HH:mm:ss.sss").format(new Date()));
		subscriptionRequest.setTradeId("hzhtmapi");
		return subscriptionRequest;
	}

	/**
	 * 获取标准利率互换市场深度行情订阅
	 * 
	 * @return
	 */
	public static XSwapMarketDataSubscriptionRequest getDeepIRSMarketDataRequest4Xswap() {
		XSwapMarketDataSubscriptionRequest subscriptionRequest = new XSwapMarketDataSubscriptionRequest();
		subscriptionRequest.setmDReqID(MockGenerateUtil.getNextMDReqID());
		subscriptionRequest.setMarketIndicator(XSwapMarketIndicatorEnum.SIRS);
		subscriptionRequest.setSecurityType(XSwapSecurityTypeEnum.SIRS);
		subscriptionRequest.setClearingMethod(XSwapClearingMethodEnum.BILATERAL);
		subscriptionRequest.setSecurityId("-");
		subscriptionRequest.setSubscriptionRequestType(XSwapSubscriptionRequestTypeEnum.ADD);
		subscriptionRequest.setPartyId("179001533010000401021");
		subscriptionRequest.setmDBookType(XswapMDBookTypeEnum.DEEP_MARKET_SUB);
		subscriptionRequest.setMarketDepth(XSwapMarketDepthEnum.DEEP_MARKET_SUB);
		subscriptionRequest.setTransactTime(new SimpleDateFormat("yyyyMMdd-HH:mm:ss.sss").format(new Date()));
		subscriptionRequest.setTradeId("hzhtmapi");
		return subscriptionRequest;
	}

	/**
	 * 获取标准利率互换市场最优行情订阅
	 * 
	 * @return
	 */
	public static XSwapMarketDataSubscriptionRequest getBestIRSMarketDataRequest4Xswap() {
		XSwapMarketDataSubscriptionRequest subscriptionRequest = new XSwapMarketDataSubscriptionRequest();
		subscriptionRequest.setmDReqID(MockGenerateUtil.getNextMDReqID());
		subscriptionRequest.setMarketIndicator(XSwapMarketIndicatorEnum.SIRS);
		subscriptionRequest.setSecurityType(XSwapSecurityTypeEnum.SIRS);
		subscriptionRequest.setClearingMethod(XSwapClearingMethodEnum.BILATERAL);
		subscriptionRequest.setSecurityId("-");
		subscriptionRequest.setSubscriptionRequestType(XSwapSubscriptionRequestTypeEnum.ADD);
		subscriptionRequest.setPartyId("179001533010000401021");
		subscriptionRequest.setmDBookType(XswapMDBookTypeEnum.BEST_MARKET_SUB);
		subscriptionRequest.setMarketDepth(XSwapMarketDepthEnum.BEST_MARKET_SUB);
		subscriptionRequest.setTransactTime(new SimpleDateFormat("yyyyMMdd-HH:mm:ss.sss").format(new Date()));
		subscriptionRequest.setTradeId("hzhtmapi");
		return subscriptionRequest;
	}
	
	
	/**
	 * 取消利率互换市场深度行情订阅
	 * 
	 * @return
	 */
	public static XSwapMarketDataSubscriptionRequest getDeepIRMarketDataUnRequest4Xswap() {
		XSwapMarketDataSubscriptionRequest subscriptionRequest = new XSwapMarketDataSubscriptionRequest();
		subscriptionRequest.setmDReqID(MockGenerateUtil.getNextMDReqID());
		subscriptionRequest.setMarketIndicator(XSwapMarketIndicatorEnum.IRS);
		subscriptionRequest.setSecurityType(XSwapSecurityTypeEnum.IRS);
		subscriptionRequest.setClearingMethod(XSwapClearingMethodEnum.BILATERAL);
		subscriptionRequest.setSecurityId("-");
		subscriptionRequest.setSubscriptionRequestType(XSwapSubscriptionRequestTypeEnum.CANCEL);
		subscriptionRequest.setPartyId("179001533010000401021");
		subscriptionRequest.setmDBookType(XswapMDBookTypeEnum.DEEP_MARKET_SUB);
		subscriptionRequest.setMarketDepth(XSwapMarketDepthEnum.DEEP_MARKET_SUB);
		subscriptionRequest.setTransactTime(new SimpleDateFormat("yyyyMMdd-HH:mm:ss.sss").format(new Date()));
		subscriptionRequest.setTradeId("hzhtmapi");
		return subscriptionRequest;
	}

	/**
	 * 取消利率互换市场最优行情订阅
	 * 
	 * @return
	 */
	public static XSwapMarketDataSubscriptionRequest getBestIRMarketDataUnRequest4Xswap() {
		XSwapMarketDataSubscriptionRequest subscriptionRequest = new XSwapMarketDataSubscriptionRequest();
		subscriptionRequest.setmDReqID(MockGenerateUtil.getNextMDReqID());
		subscriptionRequest.setMarketIndicator(XSwapMarketIndicatorEnum.IRS);
		subscriptionRequest.setSecurityType(XSwapSecurityTypeEnum.IRS);
		subscriptionRequest.setClearingMethod(XSwapClearingMethodEnum.BILATERAL);
		subscriptionRequest.setSecurityId("-");
		subscriptionRequest.setSubscriptionRequestType(XSwapSubscriptionRequestTypeEnum.CANCEL);
		subscriptionRequest.setPartyId("179001533010000401021");
		subscriptionRequest.setmDBookType(XswapMDBookTypeEnum.BEST_MARKET_SUB);
		subscriptionRequest.setMarketDepth(XSwapMarketDepthEnum.BEST_MARKET_SUB);
		subscriptionRequest.setTransactTime(new SimpleDateFormat("yyyyMMdd-HH:mm:ss.sss").format(new Date()));
		subscriptionRequest.setTradeId("hzhtmapi");
		return subscriptionRequest;
	}

	/**
	 * 取消标准利率互换市场深度行情订阅
	 * 
	 * @return
	 */
	public static XSwapMarketDataSubscriptionRequest getDeepIRSMarketDataUnRequest4Xswap() {
		XSwapMarketDataSubscriptionRequest subscriptionRequest = new XSwapMarketDataSubscriptionRequest();
		subscriptionRequest.setmDReqID(MockGenerateUtil.getNextMDReqID());
		subscriptionRequest.setMarketIndicator(XSwapMarketIndicatorEnum.SIRS);
		subscriptionRequest.setSecurityType(XSwapSecurityTypeEnum.SIRS);
		subscriptionRequest.setClearingMethod(XSwapClearingMethodEnum.BILATERAL);
		subscriptionRequest.setSecurityId("-");
		subscriptionRequest.setSubscriptionRequestType(XSwapSubscriptionRequestTypeEnum.CANCEL);
		subscriptionRequest.setPartyId("179001533010000401021");
		subscriptionRequest.setmDBookType(XswapMDBookTypeEnum.DEEP_MARKET_SUB);
		subscriptionRequest.setMarketDepth(XSwapMarketDepthEnum.DEEP_MARKET_SUB);
		subscriptionRequest.setTransactTime(new SimpleDateFormat("yyyyMMdd-HH:mm:ss.sss").format(new Date()));
		subscriptionRequest.setTradeId("hzhtmapi");
		return subscriptionRequest;
	}

	/**
	 * 取消标准利率互换市场最优行情订阅
	 * 
	 * @return
	 */
	public static XSwapMarketDataSubscriptionRequest getBestIRSMarketDataUnRequest4Xswap() {
		XSwapMarketDataSubscriptionRequest subscriptionRequest = new XSwapMarketDataSubscriptionRequest();
		subscriptionRequest.setmDReqID(MockGenerateUtil.getNextMDReqID());
		subscriptionRequest.setMarketIndicator(XSwapMarketIndicatorEnum.SIRS);
		subscriptionRequest.setSecurityType(XSwapSecurityTypeEnum.SIRS);
		subscriptionRequest.setClearingMethod(XSwapClearingMethodEnum.BILATERAL);
		subscriptionRequest.setSecurityId("-");
		subscriptionRequest.setSubscriptionRequestType(XSwapSubscriptionRequestTypeEnum.CANCEL);
		subscriptionRequest.setPartyId("179001533010000401021");
		subscriptionRequest.setmDBookType(XswapMDBookTypeEnum.BEST_MARKET_SUB);
		subscriptionRequest.setMarketDepth(XSwapMarketDepthEnum.BEST_MARKET_SUB);
		subscriptionRequest.setTransactTime(new SimpleDateFormat("yyyyMMdd-HH:mm:ss.sss").format(new Date()));
		subscriptionRequest.setTradeId("hzhtmapi");
		return subscriptionRequest;
	}

}
