package com.xquant.platform.component.darren.web.spi;

import java.util.List;

import org.springframework.stereotype.Component;

import com.xquant.platform.component.cfets.facade.spi.QuoteOrderFillServiceProvider;
import com.xquant.platform.component.entity.instrument.PledgeBond;
import com.xquant.platform.component.itf.cfets.api.dto.quote.pledgerepo.PledgeRepoDialogueQuoteOrder;
import com.xquant.platform.component.itf.cfets.common.api.dto.quote.QuoteOrder;
import com.xquant.platform.component.itf.cfets.common.api.enums.common.IOpTypeEnum;
import com.xquant.platform.component.itf.cfets.common.api.enums.common.OpBitEnum;
import com.xquant.platform.component.trade.api.dto.account.CashAccount4Self;
import com.xquant.platform.component.trade.api.dto.account.SecuAccount4Self;

@Component
public class CfetsQuoteOrderFillServiceProvider implements QuoteOrderFillServiceProvider {

	@Override
	public void fill(IOpTypeEnum opType, boolean isSelfQuote, QuoteOrder quoteOrder) {

		SecuAccount4Self secuAcct = new SecuAccount4Self();
		secuAcct.setSecuAcctId("222");
		secuAcct.setExtSecuAcctId("340");

		quoteOrder.setSecuAccount4Self(secuAcct);

		CashAccount4Self cashAcct = new CashAccount4Self();
		cashAcct.setCashAcctId("33");
		cashAcct.setExtCashAcctId("360");
		quoteOrder.setCashAccount4Self(cashAcct);

		quoteOrder.setTraderId("htcsapidealer");

		// 在接收质押式回购时填写机构号
		if (quoteOrder instanceof PledgeRepoDialogueQuoteOrder) {
			PledgeRepoDialogueQuoteOrder pledgeRepoDialogueQuoteOrder = (PledgeRepoDialogueQuoteOrder) quoteOrder;
			pledgeRepoDialogueQuoteOrder.setiId(130L);

			if ((OpBitEnum.ADD.equals(opType.getOpBit()) || OpBitEnum.UPDATE.equals(opType.getOpBit()))
					&& !isSelfQuote) {
				List<PledgeBond> pledgeBonds = ((PledgeRepoDialogueQuoteOrder) quoteOrder).getPledgeBonds();
				for (PledgeBond pledgeBond : pledgeBonds) {
					pledgeBond.setSecuAcctId(quoteOrder.getSecuAccount4Self().getSecuAcctId());
					pledgeBond.setExtSecuAcctId(quoteOrder.getSecuAccount4Self().getExtSecuAcctId());
				}
			}

		}
	}
}
