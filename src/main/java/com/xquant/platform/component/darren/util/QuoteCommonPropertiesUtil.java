package com.xquant.platform.component.darren.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import com.xquant.platform.component.commons.ATypeDefines;
import com.xquant.platform.component.commons.DayCounterEnum;
import com.xquant.platform.component.commons.ExeMarketEnum;
import com.xquant.platform.component.commons.MTypeDefines;
import com.xquant.platform.component.commons.MarketTypeEnum;
import com.xquant.platform.component.commons.SetTypeEnum;
import com.xquant.platform.component.commons.TradeTypeEnum;
import com.xquant.platform.component.compute.api.ComputeService;
import com.xquant.platform.component.entity.instrument.InstrumentKey;
import com.xquant.platform.component.entity.instrument.PledgeBond;
import com.xquant.platform.component.entity.instrument.PledgeBond.PledgeBondPartyTypeEnum;
import com.xquant.platform.component.itf.cfets.api.dto.quote.bond.BondSingleSideQuoteOrder;
import com.xquant.platform.component.itf.cfets.api.dto.quote.pledgerepo.PledgeRepoDialogueQuoteOrder;
import com.xquant.platform.component.itf.cfets.api.enums.quote.ClearingMethodEnum;
import com.xquant.platform.component.itf.cfets.common.api.dto.quote.QuoteOrder;
import com.xquant.platform.component.itf.cfets.xswap.api.dto.XSwapQuoteOrder;
import com.xquant.platform.component.trade.api.dto.account.CashAccount4Self;
import com.xquant.platform.component.trade.api.dto.account.SecuAccount4Self;
import com.xquant.platform.component.util.DateUtils;

/**
 * @author guanglai.zhou
 * @version 2017年11月29日 上午10:45:16 类说明
 */
public class QuoteCommonPropertiesUtil {

	private static Random random = new Random();

	private final static int AI_OFF_SET = 0;

	/**
	 * 增加报价时的净价
	 */
	private static final BigDecimal NET_PRICE_ADD = new BigDecimal("99.9");

	/**
	 * 报价量
	 */
	public static final BigDecimal QUOTE_VOLUME = new BigDecimal("500000");

	/**
	 * 设置报价共有属性
	 * <li>交易方向 随机买或卖</li>
	 * <li>报价有效时间 28分钟</li>
	 * <li>债券代码 1080023</li>
	 * <li>债券名称 10国网债02</li>
	 * <li>债券类型 SPT_BD</li>
	 * <li>市场类型 银行间场</li>
	 * <li>执行市场 银行间场内</li>
	 * <li>结算日期为当天</li>
	 * <li>清算速度为1</li>
	 * <li>交易数量为 100000</li>
	 * <li>占用额度(券面总额)为 10000000</li>
	 * <li>交易日期 从数据库中获取</li>
	 * 
	 * 如果为单边报价 增加如下属性
	 * <li>净价 99.9</li>
	 * <li>结算方式 DVP</li>
	 * <li>清算方式 全额付款</li> 通过计算工具计算如下参数
	 * <li>应计利息</li>
	 * <li>应计利息总额</li>
	 * <li>结算金额</li>
	 * <li>全价</li>
	 * <li>到期收益率</li>
	 * 
	 * @param quoteOrder
	 */
	public static void setCommonProperties(ComputeService computeService, QuoteOrder quoteOrder) {

		if (random.nextDouble() > 0.5) {
			quoteOrder.setTrdType(TradeTypeEnum.TRADE_TYPE_BOND_IN);
		} else {
			quoteOrder.setTrdType(TradeTypeEnum.TRADE_TYPE_BOND_OUT);
		}
		quoteOrder.setWaitUntilTime(DateTimeUtil.timeAfterMinuteWithFormat(10));
		quoteOrder.setICode("1080023");
//		quoteOrder.setICode("1789008");
		quoteOrder.setIName("10国网债02");
//		quoteOrder.setIName("17金诚1C");
		quoteOrder.setAType(ATypeDefines.A_TYPE_CODE_SPT_BD);
//		quoteOrder.setAType(ATypeDefines.A_TYPE_CODE_SPT_ABS);
		quoteOrder.setMType(MarketTypeEnum.X_CNBD);
		quoteOrder.setExeMarket(ExeMarketEnum.X_CNBD_IN);
		quoteOrder.setSettleDate(DateFormatUtils.ISO_DATE_FORMAT.format(new Date()));
		quoteOrder.setSetDays(1);
		quoteOrder.setVolume(QUOTE_VOLUME);
		// 审批单占用额度=券面总额 = 100 * 数量
		quoteOrder.setOccupyAmount(quoteOrder.getVolume().multiply(new BigDecimal("100")));
		quoteOrder.setOrdDate(DateUtils.getBussinessDate());

		if (BondSingleSideQuoteOrder.class.isInstance(quoteOrder)) {
			BondSingleSideQuoteOrder bondSingleSideQuoteOrder = (BondSingleSideQuoteOrder) quoteOrder;
			bondSingleSideQuoteOrder.setNetPrice(NET_PRICE_ADD);
			bondSingleSideQuoteOrder.setSetType(SetTypeEnum.DVP);
			bondSingleSideQuoteOrder.setClearingMethod(ClearingMethodEnum.BILATERAL);
			fillWithCompute(computeService, bondSingleSideQuoteOrder);
		} else if (PledgeRepoDialogueQuoteOrder.class.isInstance(quoteOrder)) {

			// 首期结算日推后一天
			quoteOrder.setSettleDate(DateTimeUtil.timeAfter(DateFormatUtils.ISO_DATE_FORMAT.format(new Date()),
					"yyyy-MM-dd", Calendar.DAY_OF_MONTH, 1));
			PledgeRepoDialogueQuoteOrder pledgeRepoDialogueQuoteOrder = (PledgeRepoDialogueQuoteOrder) quoteOrder;

			if (random.nextDouble() > 0.5) {
				quoteOrder.setTrdType(TradeTypeEnum.TRADE_TYPE_PLEDGE_REPO_IN);
			} else {
				quoteOrder.setTrdType(TradeTypeEnum.TRADE_TYPE_PLEDGE_REPO_OUT);
			}
			pledgeRepoDialogueQuoteOrder.setClearingMethod(ClearingMethodEnum.BILATERAL);
			pledgeRepoDialogueQuoteOrder.setOccupyAmount(quoteOrder.getVolume());
			// 回购期限
			pledgeRepoDialogueQuoteOrder.setTermDays(22);
			// 到期结算内为当日加上回购期限
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, pledgeRepoDialogueQuoteOrder.getTermDays()+1);
			pledgeRepoDialogueQuoteOrder.setEndSettleDate(DateFormatUtils.ISO_DATE_FORMAT.format(calendar));
			pledgeRepoDialogueQuoteOrder.setEndSetType(SetTypeEnum.DVP);
			// 回购利率
			pledgeRepoDialogueQuoteOrder.setRate(new BigDecimal(0.055));
			pledgeRepoDialogueQuoteOrder.setSetType(SetTypeEnum.DVP);
			// 交易品种
			pledgeRepoDialogueQuoteOrder.setRepoTradeVariety("R001");
			// 质押券列表
			ArrayList<PledgeBond> pbs = new ArrayList<PledgeBond>();
			PledgeBond pb1 = new PledgeBond();
			// 质押券资产类型
			pb1.setpAType(ATypeDefines.A_TYPE_CODE_SPT_BD);
			// 质押券代码
			pb1.setpICode("020005");
			// 质押券名称
			pb1.setpIName("02国债05");
			// 市场类型
			pb1.setmType(MTypeDefines.M_TYPE_CODE_X_CNBD);
			// 质押券市场类型
			pb1.setpMType(MarketTypeEnum.X_CNBD);
			// 质押券券面总额,单位元,整数
			pb1.setAmount(new BigDecimal(12000000));
			// 折价金额(无用)
			pb1.setDisAmount(new BigDecimal(90));
			// 折算比例,精度要求为4 0-1之间
			pb1.setDisCount(new BigDecimal(0.98));
			// 债券张数(无用)
			pb1.setVolume(QUOTE_VOLUME);
			// 是否为本方
			pb1.setPartyType(PledgeBondPartyTypeEnum.PARTY);
			// 内部证券账户ID
			pb1.setSecuAcctId("221");
			// 外部证券账户ID
			pb1.setExtSecuAcctId("200");
			pbs.add(pb1);
			pledgeRepoDialogueQuoteOrder.setPledgeBonds(pbs);

			// 计息基准(默认)
			pledgeRepoDialogueQuoteOrder.setDayCounter(DayCounterEnum.A_365);
			fillWithCompute4Repo(computeService, pledgeRepoDialogueQuoteOrder,
					pledgeRepoDialogueQuoteOrder.getPledgeBonds());
		}
	}

	/**
	 * 设置本方内证 外证 内资 外资 外汇交易中心托管账户和资金账户 本方交易员内部id 外部id 交易员名称
	 * 
	 * @param quoteOrder
	 */
	public static void setRequestSideInfo(QuoteOrder quoteOrder) {
		setRequestSideInfoOfhtcs(quoteOrder);
	}
	
	
	/**
	 * 设置本方内证 外证 内资 外资 外汇交易中心托管账户和资金账户 本方交易员内部id 外部id 交易员名称
	 * 
	 * @param quoteOrder
	 */
	public static void setRequestSideInfoOfhtcs(QuoteOrder quoteOrder) {
		SecuAccount4Self secuAcct = new SecuAccount4Self();
		secuAcct.setSecuAcctId("222");
		secuAcct.setExtSecuAcctId("340");
		// 托管账号
		secuAcct.setCustodianAcctNumber("0980980987");
		secuAcct.setCustodianPartyName("国债登记结算公司");
		quoteOrder.setSecuAccount4Self(secuAcct);

		CashAccount4Self cashAcct = new CashAccount4Self();
		cashAcct.setExtCashAcctId("360");
		cashAcct.setCashAcctId("33");
		// 资金账号
		cashAcct.setAcctNumber("98769876987607");
		cashAcct.setBankName("杭州衡泰测试");
		quoteOrder.setCashAccount4Self(cashAcct);

		// 本方交易员id
		quoteOrder.setTraderId("htcsinnerdealer");
		// 本方的交易员外汇交易中心ID
		quoteOrder.setTraderCfetsId("htcsapidealer");
		// 本方交易员
		quoteOrder.setTrader("a079");
	}
	
	
	/**
	 * 设置本方内证 外证 内资 外资 外汇交易中心托管账户和资金账户 本方交易员内部id 外部id 交易员名称
	 * 
	 * @param quoteOrder
	 */
	public static void setRequestSideInfoOfdwjj(QuoteOrder quoteOrder) {
		SecuAccount4Self secuAcct = new SecuAccount4Self();
		secuAcct.setSecuAcctId("225");
		secuAcct.setExtSecuAcctId("343");
		// 托管账号
		secuAcct.setCustodianAcctNumber("00000003808");
		secuAcct.setCustodianPartyName("国债登记结算公司");
		quoteOrder.setSecuAccount4Self(secuAcct);

		CashAccount4Self cashAcct = new CashAccount4Self();
		cashAcct.setExtCashAcctId("362");
		cashAcct.setCashAcctId("35");
		// 资金账号
		cashAcct.setAcctNumber("03492300040005360");
		cashAcct.setBankName("中国农业银行上海市分行第二营业部(大额支付号：103290028025联行行行号：092802)");
		quoteOrder.setCashAccount4Self(cashAcct);

		// 本方交易员id
		quoteOrder.setTraderId("dwjjdealer1");
		// 本方的交易员外汇交易中心ID
		quoteOrder.setTraderCfetsId("dwjjdealer1");
		// 本方交易员
		quoteOrder.setTrader("东吴基金首席交易员");
	}

	

	/**
	 * 设置交易对手交易员名称、编号、机构代码、对手系统内ID
	 * 
	 * @param quoteOrder
	 */
	public static void setResponseSideInfo(QuoteOrder quoteOrder) {
		// 对手交易员名称
		if (StringUtils.isBlank(quoteOrder.getTraderCp())) {
			quoteOrder.setTraderCp("a079");
		}
		// quoteOrder.setTraderCp("a506");
		// 对手交易员编号
		if (StringUtils.isBlank(quoteOrder.getTraderCpCfetsId())) {
			quoteOrder.setTraderCpCfetsId("htdealergsab");
		}
		// quoteOrder.setTraderCpCfetsId("htjjdealerdwjj");
		// quoteOrder.setTraderCpCfetsId("lwyhdealercdb");
		// 接收方机构代码 恒天基金
		if (StringUtils.isBlank(quoteOrder.getCountryPartyCfetsId())) {
			quoteOrder.setCountryPartyCfetsId("891012844010011610011");
			quoteOrder.setCounterPartyName("广发基金公司工行广发主题投资资产管理计划9号");
			// quoteOrder.setCountryPartyCfetsId("888888888888888888888");
		}
		// quoteOrder.setCountryPartyCfetsId("111111111111111111111");
		// 对手方系统内ID
		if (quoteOrder.getCountryPartyId() == null) {
			quoteOrder.setCountryPartyId(1481L);
		}

	}

	/**
	 * 设置本方债券账号、资金账号信息、交易员编号
	 * 
	 * @param quoteOrder
	 */
	public static void setRequestSideInfo4Xswap(XSwapQuoteOrder quoteOrder) {
		SecuAccount4Self secuAcct = new SecuAccount4Self();
		secuAcct.setSecuAcctId("223");
		secuAcct.setExtSecuAcctId("341");
		// 托管账号
		secuAcct.setCustodianAcctNumber("0980980987");
		secuAcct.setCustodianPartyName("国债登记结算公司");
		quoteOrder.setSecuAccount4Self(secuAcct);

		CashAccount4Self cashAcct = new CashAccount4Self();
		cashAcct.setExtCashAcctId("361");
		cashAcct.setCashAcctId("34");
		// 资金账号
		cashAcct.setAcctNumber("98769876987607");
		cashAcct.setBankName("杭州衡泰测试");

		quoteOrder.setCashAccount4Self(cashAcct);
		// 本方交易员id
		quoteOrder.setTraderId("hzhtcsdealer");
		// 本方的交易员外汇交易中心ID
		quoteOrder.setTraderCfetsId("hzhtcsdealer");
		// 本方交易员
		quoteOrder.setTrader("a079");

	}

	/**
	 * 设置交易对手交易员名称、编号、机构代码
	 * 
	 * @param quoteOrder
	 */
	public static void setResponseSideInfo4Xswap(QuoteOrder quoteOrder) {
		// 对手交易员名称
		// quoteOrder.setTraderCp("a079");
		// quoteOrder.setTraderCp("a506");
		// 对手交易员编号
		// quoteOrder.setTraderCpCfetsId("htjjdealerdwjj");
		// quoteOrder.setTraderCpCfetsId("lwyhdealercdb");
		// 接收方机构代码 恒天基金
		quoteOrder.setTraderCpCfetsId("-1");
		quoteOrder.setCountryPartyCfetsId("-1");
		quoteOrder.setCountryPartyId(-1L);
		// quoteOrder.setCountryPartyCfetsId("111111111111111111111");
		// 对手方系统内ID
		// quoteOrder.setCountryPartyId(1666L);
	}

	/**
	 * 计算应计利息 应计利息总额 全价 到期收益率 结算金额
	 * 
	 * @param computeService
	 * @param quoteOrder
	 */
	public static void fillWithCompute(ComputeService computeService, BondSingleSideQuoteOrder quoteOrder) {
		// 指定key
		InstrumentKey instrumentKey = ComputeServiceUtil.getInstrumentKey(quoteOrder.getICode(), quoteOrder.getAType(),
				quoteOrder.getMType().getValue());
		// 应计利息
		double ai = ComputeServiceUtil.getai(computeService, instrumentKey, quoteOrder.getSettleDate(), AI_OFF_SET);
		quoteOrder.setAi(new BigDecimal(ai));
		// 应计利息总额 = 应计利息 * 数量
		quoteOrder.setTotalAi(quoteOrder.getAi().multiply(quoteOrder.getVolume()));

		// 交易金额 = 净价 * 数量
		// 结算金额 = 交易金额 + 应计利息总额 = 全价 * 数量
		quoteOrder.setSettleAmount(
				quoteOrder.getNetPrice().multiply(quoteOrder.getVolume()).add(quoteOrder.getTotalAi()));
		// 计算全价
		quoteOrder.setFullPrice(quoteOrder.getSettleAmount().divide(quoteOrder.getVolume()));
		// 计算到期收益率
		double ytm = ComputeServiceUtil.getYtmViaNetPrice(computeService, instrumentKey, quoteOrder.getSettleDate(),
				AI_OFF_SET, quoteOrder.getNetPrice().toString());
		quoteOrder.setYtm(
				new BigDecimal(ytm).divide(new BigDecimal("1"), 4, RoundingMode.HALF_UP).divide(new BigDecimal("100")));
	}

	/**
	 * 计算质押式回购的券面总额 结算金额 首期结算金额 到期结算金额
	 * 
	 * @param computeService
	 * @param quoteOrder
	 * @param pledgeBonds
	 */
	public static void fillWithCompute4Repo(ComputeService computeService, PledgeRepoDialogueQuoteOrder quoteOrder,
			List<PledgeBond> pledgeBonds) {
		quoteOrder.setOccupyAmount(new BigDecimal("0"));
		quoteOrder.setSettleAmount(new BigDecimal("0"));
		quoteOrder.setEndSettleAmount(new BigDecimal("0"));
		// 质押式回购计算公式
		// 首期结算金额 = 交易金额 = 券面总额 * 折算比例
		// 应计利息 = 交易金额* 回购利率 * 回购期限/365
		// 到期结算金额 = 交易金额 + 应计利息
		for (PledgeBond pledgeBond : pledgeBonds) {
			// 单个交易金额
			BigDecimal singleTradeAmt = pledgeBond.getAmount().multiply(pledgeBond.getDisCount());
			quoteOrder.setSettleAmount(quoteOrder.getSettleAmount().add(singleTradeAmt));
			// 单个应计利息
			BigDecimal ytm = singleTradeAmt.multiply(quoteOrder.getRate())
					.multiply(new BigDecimal(quoteOrder.getTermDays()))
					.divide(new BigDecimal("365"), 2, RoundingMode.HALF_UP);
			// 单个到期结算金额
			BigDecimal singleEndsettleAmount = singleTradeAmt.add(ytm);
			quoteOrder.setEndSettleAmount(quoteOrder.getEndSettleAmount().add(singleEndsettleAmount));
		}

	}

}
