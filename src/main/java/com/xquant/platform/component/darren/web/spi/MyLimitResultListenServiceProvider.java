package com.xquant.platform.component.darren.web.spi;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.xquant.platform.component.cfets.facade.spi.LimitResultListenServiceProvider;
import com.xquant.platform.component.cfets.facade.spi.RiskLimitResultEvent;
import com.xquant.platform.component.darren.vo.Greeting;
import com.xquant.platform.component.limit.entity.LimitRuleCalcResultVO;
import com.xquant.platform.component.limit.entity.credit.LimitChg;
import com.xquant.platform.component.limit.entity.credit.LimitItem;
import com.xquant.platform.component.service.facade.trade.LimitResult;

/**
 * Copyright © 2018 xQuant Info. Tech Ltd. All rights reserved.
 * 
 * @Package: com.xquant.platform.component.back.spi
 * @author: guanglai.zhou
 * @date: 2018-07-04 10:09:55
 */
@Component
public class MyLimitResultListenServiceProvider implements LimitResultListenServiceProvider {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	private ExecutorService fixedPool = Executors.newFixedThreadPool(5);

	@Override
	public void onOccupy(RiskLimitResultEvent riskLimitResultEvent) {

		/**
		 * 用于接收限额占用结果
		 */
		System.out.println("=================用于接收限额占用结果=================="
				+ new SimpleDateFormat("yyyyMMdd HH:mm:ss.sss").format(new Date()));

		// 由接口提供用于判断是否限额占用的的限额上下文参数 可用于异常分析 正常情况下可以不进行关心
//		RiskLimitContextArg riskLimitContextArg = riskLimitResultEvent.getRiskLimitContextArg();
		// 由项目组填写的限额调用参数 可用于异常分析 正常情况下可以不进行关心
//		RiskLimitCallArg callArg = riskLimitResultEvent.getCallArg();
		// 获取限额结果 项目关心的限额占用结果
		LimitResult limitResult = riskLimitResultEvent.getLimitResult();

		// 判断限额额度是否通过 已弃用
		// boolean isSuccessed = limitResult.isSuccessed();

		if (limitResult.getLimitRuleResult() != null) {

			// 判断是否超过限额
			Boolean overrun = limitResult.getLimitRuleResult().getOverrun();
			System.out.println("overrun = " + overrun);
			if (Boolean.TRUE.equals(overrun)) {
				// 超了限额
			}

			List<LimitRuleCalcResultVO> resultItems = limitResult.getLimitRuleResult().getResultItems();
			for (LimitRuleCalcResultVO limitRuleCalcResultVO : resultItems) {
				System.out.println("levelid" + limitRuleCalcResultVO.getLevelId());
			}
		}
		if (limitResult.getOccpuyResult() != null) {
			List<LimitItem> limitItems = limitResult.getOccpuyResult().getLimitItems();
			for (LimitItem limitItem : limitItems) {
				System.out.println("freezedAmount = " + limitItem.getLimitDetail().getFreezedAmount());
				System.out.println("occupiedAmount = " + limitItem.getLimitDetail().getOccupiedAmount());
				System.out.println("remainedAmount = " + limitItem.getLimitDetail().getRemainedAmount());
				List<LimitChg> limitChgs = limitItem.getLimitChgs();
				BigDecimal totalChange = new BigDecimal("0");
				for (LimitChg limitChg : limitChgs) {
					totalChange = totalChange.add(limitChg.getRemainedAmtChg());
				}
				System.out.println("totalChange = " + totalChange);

				Greeting greeting = new Greeting("占用额度 = -10000");

				System.out.println("simpMessagingTemplate = " + simpMessagingTemplate);

				fixedPool.execute(new Runnable() {
					@Override
					public void run() {
						simpMessagingTemplate.convertAndSend("/topic/greetings", greeting);
					}
				});

				System.out.println("占用额度 send success =  " + totalChange);

			}
		}
	}

	@Override
	public void onRelease(RiskLimitResultEvent riskLimitResultEvent) {

		/**
		 * 用于接收限额释放结果
		 */
		System.out.println("=================开始接收释放额度结果=================="
				+ new SimpleDateFormat("yyyyMMdd HH:mm:ss.sss").format(new Date()));
		LimitResult limitResult = riskLimitResultEvent.getLimitResult();
		if (limitResult.getLimitRuleResult() != null) {
			List<LimitRuleCalcResultVO> resultItems = limitResult.getLimitRuleResult().getResultItems();
			for (LimitRuleCalcResultVO limitRuleCalcResultVO : resultItems) {
				System.out.println("levelid" + limitRuleCalcResultVO.getLevelId());
			}
		}
		if (limitResult.getOccpuyResult() != null) {
			List<LimitItem> limitItems = limitResult.getOccpuyResult().getLimitItems();
			for (LimitItem limitItem : limitItems) {
				System.out.println("freezedAmount = " + limitItem.getLimitDetail().getFreezedAmount());
				System.out.println("occupiedAmount = " + limitItem.getLimitDetail().getOccupiedAmount());
				System.out.println("remainedAmount = " + limitItem.getLimitDetail().getRemainedAmount());
				List<LimitChg> limitChgs = limitItem.getLimitChgs();
				BigDecimal totalChange = new BigDecimal("0");
				for (LimitChg limitChg : limitChgs) {
					totalChange = totalChange.add(limitChg.getRemainedAmtChg());
				}
				System.out.println("totalChange = " + totalChange);

				Greeting greeting = new Greeting("释放 = " + totalChange.toString());

				fixedPool.execute(new Runnable() {
					@Override
					public void run() {
						simpMessagingTemplate.convertAndSend("/topic/greetings", greeting);
					}
				});
			}
		}
	}

}
