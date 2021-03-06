package com.xquant.platform.component.darren.util;

import java.text.SimpleDateFormat;

import org.apache.commons.lang3.time.DateFormatUtils;

/**   
 * Copyright © 2018 xQuant Info. Tech Ltd. All rights reserved.
 * 
 * @Package: com.xquant.platform.component.back.test 
 * @author: guanglai.zhou   
 * @date: 2018-05-03 11:20:50
 */
public class DatePatternUtil extends DateFormatUtils{

	public static final String DATE_TIME = "yyyyMMdd-HH:mm:ss.sss";

	/**
	 * yyyyMMdd的日期类型
	 */
	public static final SimpleDateFormat YYYYMMDD = new SimpleDateFormat("yyyyMMdd");

	/**
	 * yyyyMMdd-HH:mm:ss.sss 格式的pattern
	 */
	public static final SimpleDateFormat DATE_TIME_PATTERN = new SimpleDateFormat("yyyyMMdd-HH:mm:ss.sss");
	
	
}
