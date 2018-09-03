package com.xquant.platform.component.darren.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xquant.platform.component.itf.cfets.common.api.dto.quote.QuoteOrder;
import com.xquant.platform.component.itf.cfets.common.convertor.QuoteAndTradeBiConverter;
import com.xquant.platform.component.trade.api.dto.trade.base.TradeElementBase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:appContext-component-web-service.xml")
public class WebServiceTest {

	@Autowired
	List<QuoteAndTradeBiConverter<QuoteOrder, TradeElementBase>> converters;

	@Test
	public void testFindAll() {
		for (QuoteAndTradeBiConverter<QuoteOrder, TradeElementBase> quoteAndTradeBiConverter : converters) {
			System.out.println(quoteAndTradeBiConverter.getQuoteOrderClass());
		}
	}

}
