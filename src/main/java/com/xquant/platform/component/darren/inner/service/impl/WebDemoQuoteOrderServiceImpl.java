package com.xquant.platform.component.darren.inner.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.xquant.platform.component.commons.TradeStatusEnum;
import com.xquant.platform.component.darren.dao.WebDemoQuoteOrderMapper;
import com.xquant.platform.component.darren.inner.service.WebDemoQuoteOrderService;
import com.xquant.platform.component.darren.vo.WebDemoQuoteOrder;
import com.xquant.platform.component.itf.cfets.common.api.enums.common.QuoteBizTypeEnum;

@Service
public class WebDemoQuoteOrderServiceImpl implements WebDemoQuoteOrderService {

	@Autowired
	private WebDemoQuoteOrderMapper webDemoQuoteOrderMapper;
	
	private static final String DATE_FORMAT = "yyyyMMdd";

	private DateFormat format = new SimpleDateFormat(DATE_FORMAT);

	@Override
	public Map<String, Object> getAllQuoteOrderByDate(Date date) {
		Map<String, Object> map = new HashMap<>();
		String formatedDate = format.format(date);
		int count = webDemoQuoteOrderMapper.count(formatedDate);
		List<WebDemoQuoteOrder> allQuoteOrders = webDemoQuoteOrderMapper.findAll(formatedDate);
		return filter(allQuoteOrders, count, map);
	}

	/**
	 * 过滤掉报价状态为-1的对话报价和请求回复报价 属于已成交的报价 不需要再进行交易
	 * 
	 * @param allQuoteOrders
	 * @param count
	 * @param map
	 * @return
	 */
	private Map<String, Object> filter(List<WebDemoQuoteOrder> allQuoteOrders, int count, Map<String, Object> map) {
		List<WebDemoQuoteOrder> newAllQuoteOrders = new ArrayList<>();
		Set<String> needFilterd = new HashSet<>();
		needFilterd.add(QuoteBizTypeEnum.BOND_DLG.getValue());
		needFilterd.add(QuoteBizTypeEnum.PLEDGEREPO_DLG.getValue());
		needFilterd.add(QuoteBizTypeEnum.BOND_RFQ_REPLY.getValue());

		for (WebDemoQuoteOrder webDemoQuoteOrder : allQuoteOrders) {
			// 属于对话报价和请求回复报价 以及报价状态为-1的
			if (needFilterd.contains(webDemoQuoteOrder.getQuoteType())
					&& String.valueOf(TradeStatusEnum.ORDER_STATE_APPROVED.getValue())
							.equals(webDemoQuoteOrder.getOrdstatus())) {
				count--;
			} else {
				newAllQuoteOrders.add(webDemoQuoteOrder);
			}
		}
		map.put("count", count);
		map.put("allQuoteOrders", newAllQuoteOrders);
		return map;
	}

	@Override
	public List<WebDemoQuoteOrder> getByQuoteId(String quoteId) {
		Assert.notNull(quoteId, "quoteId 不允许为空！");
		return webDemoQuoteOrderMapper.findByQuoteId(quoteId);
	}

}
