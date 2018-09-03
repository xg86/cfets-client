package com.xquant.platform.component.darren.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xquant.platform.component.darren.vo.WebDemoQuoteOrder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:appContext-component-web-dao.xml")
public class WebDemoQuoteOrderMapperTest {

	@Autowired
	private WebDemoQuoteOrderMapper webDemoQuoteOrderMapper;

	@Test
	public void testFindAll() {
		String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
		List<WebDemoQuoteOrder> allQuoteOrders = webDemoQuoteOrderMapper.findAll(date);
		for (WebDemoQuoteOrder quoteOrder : allQuoteOrders) {
			System.out.println(quoteOrder);
		}
	}

	@Test
	public void testCount() {
		String date = "%" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + "%";
		int count = webDemoQuoteOrderMapper.count(date);
		System.out.println(count);
	}

}
