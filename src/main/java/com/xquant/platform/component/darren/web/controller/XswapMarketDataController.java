package com.xquant.platform.component.darren.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xquant.platform.component.cfets.facade.market.FacadeXswapMarketDataRequestService;
import com.xquant.platform.component.darren.inner.factory.MarketDataRequestFactory;
import com.xquant.platform.component.itf.cfets.xswap.api.dto.XSwapMarketDataSubscriptionRequest;

/**
 * 
 * @Project: cfetsclienta   
 * @Package: com.xquant.platform.component.darren.web.controller
 * @author: guanglai.zhou   
 * @date: 2018-08-29 10:36:02
 */
@Controller
@RequestMapping("/xswap/market/*")
public class XswapMarketDataController extends CommonController {

	@Autowired
	private FacadeXswapMarketDataRequestService facadeXswapMarketDataRequestService;

	@RequestMapping("irbest")
	public ModelAndView irbest(HttpServletRequest request, HttpServletResponse response) {

		XSwapMarketDataSubscriptionRequest xSwapMarketDataSubscriptionRequest = MarketDataRequestFactory
				.getBestIRMarketDataRequest4Xswap();
		boolean flag = true;
		try {
			facadeXswapMarketDataRequestService.executeMarketDataRequest(xSwapMarketDataSubscriptionRequest);
		} catch (Exception e) {
			flag = false;
		}
		super.print(response, flag);
		return null;
	}

	@RequestMapping("irdeep")
	public ModelAndView irdeep(HttpServletRequest request, HttpServletResponse response) {

		XSwapMarketDataSubscriptionRequest xSwapMarketDataSubscriptionRequest = MarketDataRequestFactory
				.getDeepIRMarketDataRequest4Xswap();
		boolean flag = true;
		try {
			facadeXswapMarketDataRequestService.executeMarketDataRequest(xSwapMarketDataSubscriptionRequest);
		} catch (Exception e) {
			flag = false;
		}
		super.print(response, flag);
		return null;
	}

	@RequestMapping("irsbest")
	public ModelAndView irsbest(HttpServletRequest request, HttpServletResponse response) {

		XSwapMarketDataSubscriptionRequest xSwapMarketDataSubscriptionRequest = MarketDataRequestFactory
				.getBestIRSMarketDataRequest4Xswap();
		boolean flag = true;
		try {
			facadeXswapMarketDataRequestService.executeMarketDataRequest(xSwapMarketDataSubscriptionRequest);
		} catch (Exception e) {
			flag = false;
		}
		super.print(response, flag);
		return null;
	}

	@RequestMapping("irsdeep")
	public ModelAndView irsdeep(HttpServletRequest request, HttpServletResponse response) {

		XSwapMarketDataSubscriptionRequest xSwapMarketDataSubscriptionRequest = MarketDataRequestFactory
				.getDeepIRSMarketDataRequest4Xswap();
		boolean flag = true;
		try {
			facadeXswapMarketDataRequestService.executeMarketDataRequest(xSwapMarketDataSubscriptionRequest);
		} catch (Exception e) {
			flag = false;
		}
		super.print(response, flag);
		return null;
	}

	@RequestMapping("unirbest")
	public ModelAndView unirbest(HttpServletRequest request, HttpServletResponse response) {

		XSwapMarketDataSubscriptionRequest xSwapMarketDataSubscriptionRequest = MarketDataRequestFactory
				.getBestIRMarketDataUnRequest4Xswap();
		boolean flag = true;
		try {
			facadeXswapMarketDataRequestService.executeMarketDataRequest(xSwapMarketDataSubscriptionRequest);
		} catch (Exception e) {
			flag = false;
		}
		super.print(response, flag);
		return null;
	}

	@RequestMapping("unirdeep")
	public ModelAndView unirdeep(HttpServletRequest request, HttpServletResponse response) {

		XSwapMarketDataSubscriptionRequest xSwapMarketDataSubscriptionRequest = MarketDataRequestFactory
				.getDeepIRMarketDataUnRequest4Xswap();
		boolean flag = true;
		try {
			facadeXswapMarketDataRequestService.executeMarketDataRequest(xSwapMarketDataSubscriptionRequest);
		} catch (Exception e) {
			flag = false;
		}
		super.print(response, flag);
		return null;
	}

	@RequestMapping("unirsbest")
	public ModelAndView unirsbest(HttpServletRequest request, HttpServletResponse response) {

		XSwapMarketDataSubscriptionRequest xSwapMarketDataSubscriptionRequest = MarketDataRequestFactory
				.getBestIRSMarketDataUnRequest4Xswap();
		boolean flag = true;
		try {
			facadeXswapMarketDataRequestService.executeMarketDataRequest(xSwapMarketDataSubscriptionRequest);
		} catch (Exception e) {
			flag = false;
		}
		super.print(response, flag);
		return null;
	}

	@RequestMapping("unirsdeep")
	public ModelAndView unirsdeep(HttpServletRequest request, HttpServletResponse response) {

		XSwapMarketDataSubscriptionRequest xSwapMarketDataSubscriptionRequest = MarketDataRequestFactory
				.getDeepIRSMarketDataUnRequest4Xswap();
		boolean flag = true;
		try {
			facadeXswapMarketDataRequestService.executeMarketDataRequest(xSwapMarketDataSubscriptionRequest);
		} catch (Exception e) {
			flag = false;
		}
		super.print(response, flag);
		return null;
	}

}
