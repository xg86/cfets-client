package com.xquant.platform.component.darren.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xquant.platform.component.darren.vo.WebDemoQuoteOrder;

@Repository
public interface WebDemoQuoteOrderMapper {

	/**
	 * 查询某天的全部有效报价
	 * 
	 * @param date
	 * @return
	 */
	public List<WebDemoQuoteOrder> findAll(@Param("date") String date);

	/**
	 * 查询某天的全部有效报价条数                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
	 * 
	 * @param date
	 * @return
	 */
	public int count(@Param("date") String date);

	/**
	 * 通过quoteId进行数据查询
	 * @return
	 */
	public List<WebDemoQuoteOrder> findByQuoteId(@Param("quoteId") String quoteId);

}
