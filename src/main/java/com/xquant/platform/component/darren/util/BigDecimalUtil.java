package com.xquant.platform.component.darren.util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

import org.apache.commons.lang3.reflect.FieldUtils;

import com.xquant.cfets.trade.protocol.message.CfetsTradeBondDialogueQuoteResMessageBody;

/**
 * Copyright © 2018 xQuant Info. Tech Ltd. All rights reserved.
 * 
 * @Package: com.xquant.platform.component.back.util
 * @author: guanglai.zhou
 * @date: 2018-06-13 10:19:21
 */
public class BigDecimalUtil {

	/**
	 * 随机获取一个指定精度的BigDecimal类型数据
	 * 
	 * @param scale
	 * @return
	 */
	public static BigDecimal randDeciWithFixedScale(int scale) {
		Random rand = new Random();
		double dou = rand.nextDouble();
		return new BigDecimal(dou).divide(new BigDecimal("1"), scale, RoundingMode.HALF_UP);
	}

	public static void main(String[] args) {

		Field[] allFields = FieldUtils.getAllFields(CfetsTradeBondDialogueQuoteResMessageBody.class);
		for (Field field : allFields) {
			System.out.println(field.getName());
		}
	}

}
