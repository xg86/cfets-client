package com.xquant.platform.component.darren.inner.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.xquant.platform.component.darren.vo.WebDemoQuoteOrder;

public interface WebDemoQuoteOrderService {

	/**
	 * 获取当前日期的所有有效的报价 只交易表中状态为-2和-1的
	 * 
	 * @param date 当前日期
	 * @return 包含所有报价对象的List集合allQuoteOrders和对象的数量count
	 */
	public Map<String, Object> getAllQuoteOrderByDate(Date date);
	
	/**
	 * 根据quoteId进行报价的查询
	 * @param quoteId
	 * @return
	 */
	public List<WebDemoQuoteOrder> getByQuoteId(String quoteId);
}
