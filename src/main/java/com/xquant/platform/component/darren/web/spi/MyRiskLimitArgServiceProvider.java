package com.xquant.platform.component.darren.web.spi;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.xquant.platform.component.cfets.facade.spi.ActionSide;
import com.xquant.platform.component.cfets.facade.spi.RiskLimitArgServiceProvider;
import com.xquant.platform.component.cfets.facade.spi.RiskLimitCallArg;
import com.xquant.platform.component.cfets.facade.spi.RiskLimitContextArg;
import com.xquant.platform.component.itf.cfets.api.dto.quote.bond.BondDialogueQuoteOrder;
import com.xquant.platform.component.itf.cfets.api.dto.quote.bond.BondRfqReplyQuoteOrder;
import com.xquant.platform.component.itf.cfets.api.dto.quote.pledgerepo.PledgeRepoDialogueQuoteOrder;
import com.xquant.platform.component.itf.cfets.common.api.dto.quote.QuoteOrder;
import com.xquant.platform.component.itf.cfets.common.api.enums.InvokeDrivenSourceEnum;
import com.xquant.platform.component.itf.cfets.common.api.enums.common.IOpTypeEnum;
import com.xquant.platform.component.itf.cfets.common.api.enums.common.OpBitEnum;
import com.xquant.platform.component.itf.cfets.xswap.api.dto.XSwapQuoteOrder;

/**
 * Copyright © 2018 xQuant Info. Tech Ltd. All rights reserved.
 * 
 * @Package: com.xquant.platform.component.back.test.base
 * @author: guanglai.zhou
 * @date: 2018-06-15 12:07:14
 */
@Component
public class MyRiskLimitArgServiceProvider implements RiskLimitArgServiceProvider {

	@Override
	public RiskLimitCallArg getArg4Occupy(RiskLimitContextArg riskLimitContextArg) {

		System.out.println("==========MyRiskLimitArgServiceProvider getArg4Occupy===="
				+ new SimpleDateFormat("yyyyMMdd HH:mm:ss.sss").format(new Date()));

		// 获取驱动源 判断是否前台驱动还是后台驱动
		InvokeDrivenSourceEnum invokeDrivenSource = riskLimitContextArg.getInvokeDrivenSource();
		// 获取限额动作触发方 本方还是对手方
		ActionSide actionSide = riskLimitContextArg.getActionSide();
		// 获取报价对象
		QuoteOrder quoteOrder = riskLimitContextArg.getQuoteOrder();
		// 判断是否是快照恢复模式 此模式下导致的占用和释放都是强制占用和释放的
		boolean isShotRecoverMode = riskLimitContextArg.isShotRecoverMode();
		// 获取报价操作类型
		IOpTypeEnum opType = riskLimitContextArg.getOpType();
		// 获取动作类型 可用于判断新增 修改 还是成交
		OpBitEnum opBit = opType.getOpBit();

		// 本方报价在恢复快照时需要进行限额的占用 (本方修改 对手成交)
		if (isShotRecoverMode) {
			return new RiskLimitCallArg(true, true, true);
		}
		// 本方前台新增、修改、成交 债券对话报价、质押式回购报价、请求回复报价时 报价需要占用限额
		if (ActionSide.SELF.equals(actionSide) && InvokeDrivenSourceEnum.FRONT.equals(invokeDrivenSource)
				&& isQuoteTypeNeedOccupyLimit(quoteOrder)
				&& (OpBitEnum.ADD.equals(opBit) || OpBitEnum.UPDATE.equals(opBit) || OpBitEnum.CONFIRM.equals(opBit))) {
			return new RiskLimitCallArg(true, true, false);
		}

		// 返回null代表不占用限额
		return null;
	}

	/**
	 * 该报价类型是否需要占限额
	 * 
	 * @param quoteOrder
	 * @return
	 */
	private boolean isQuoteTypeNeedOccupyLimit(QuoteOrder quoteOrder) {
		return BondDialogueQuoteOrder.class.isInstance(quoteOrder)
				|| PledgeRepoDialogueQuoteOrder.class.isInstance(quoteOrder)
				|| BondRfqReplyQuoteOrder.class.isInstance(quoteOrder)
		        || XSwapQuoteOrder.class.isInstance(quoteOrder);
	}
}
