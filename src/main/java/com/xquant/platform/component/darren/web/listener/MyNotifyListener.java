package com.xquant.platform.component.darren.web.listener;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.TypeUtils;
import org.springframework.context.MessageSource;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.xquant.platform.component.darren.inner.service.WebDemoQuoteOrderService;
import com.xquant.platform.component.darren.vo.WebDemoQuoteOrder;
import com.xquant.platform.component.itf.cfets.api.dto.quote.ExecQuoteOrder;
import com.xquant.platform.component.itf.cfets.common.api.dto.notify.NotifyMessage;
import com.xquant.platform.component.itf.cfets.common.api.dto.notify.NotifyMessage4QuoteReceive;
import com.xquant.platform.component.itf.cfets.common.api.dto.notify.NotifyMessage4QuoteRes;
import com.xquant.platform.component.itf.cfets.common.api.dto.quote.QuoteOrder;
import com.xquant.platform.component.itf.cfets.common.api.enums.common.IOpTypeEnum;
import com.xquant.platform.component.itf.cfets.common.api.enums.common.OpBitEnum;
import com.xquant.platform.component.itf.cfets.common.api.enums.common.QuoteBizTypeEnum;
import com.xquant.platform.component.itf.cfets.common.api.enums.notify.NotifyMessageTopicEnum;
import com.xquant.platform.component.itf.cfets.common.api.notify.NotifyListener;
import com.xquant.platform.component.itf.cfets.common.api.notify.NotifyMessageFilter;
import com.xquant.platform.component.itf.cfets.skeleton.enums.common.ActionCodeEnum;

//@Component
public class MyNotifyListener implements NotifyListener {

	private MessageSource msgSource;

	private SimpMessagingTemplate simpMessagingTemplate;

	private WebDemoQuoteOrderService webDemoQuoteOrderService;

	private Object synObj = new Object();

	public MyNotifyListener(MessageSource msgSource, SimpMessagingTemplate simpMessagingTemplate,
			WebDemoQuoteOrderService webDemoQuoteOrderService) {
		super();
		this.msgSource = msgSource;
		this.simpMessagingTemplate = simpMessagingTemplate;
		this.webDemoQuoteOrderService = webDemoQuoteOrderService;
	}

	@Override
	public String getClientId() {
		return "random";
	}

	@Override
	public NotifyMessageTopicEnum getTopic() {
		return NotifyMessageTopicEnum.QUOTE;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public NotifyMessageFilter getMessageFilter() {
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void accept(NotifyMessage message) {

		System.out.println("--------------------------------------------------");
		System.out.println("--------------------------------------------------");
		System.out.println(message.getHeader() + message.getClass().getName() + "\r\n"
				+ message.getHeader().getMessageSubType().getOpBit() + "\r\n" + message.getHeader().getAction());

		if (OpBitEnum.EXECREPORT.equals(message.getHeader().getMessageSubType().getOpBit())) {
			System.out.println(
					"=成交回报判断方法一========* OpBitEnum.EXECREPORT.equals(message.getHeader().getMessageSubType().getOpBit()) *=");
			System.out.println(message.getHeader());
			ExecQuoteOrder execQuoteOrder = (ExecQuoteOrder) message.getPlayLoad();
			System.out.println(execQuoteOrder);
		}

		if (ActionCodeEnum.EXECUTION_REPORT.getValue().equals(message.getHeader().getAction())) {
			System.out.println(
					"=成交回报判断方法二========* ActionCodeEnum.EXECUTION_REPORT.getValue().equals(message.getHeader().getAction()) *=");
			System.out.println(message.getHeader());
			ExecQuoteOrder execQuoteOrder = (ExecQuoteOrder) message.getPlayLoad();
			if (execQuoteOrder != null) {
				System.out.println("======进行前台成交回报推送======" + execQuoteOrder);
				synchronized (synObj) {
					simpMessagingTemplate.convertAndSend("/topic/execQuoteOrder", execQuoteOrder);
				}
				System.out.println("======前台成交回报推送完毕======");
			}
		}
		
		// 只处理res和receive结果
		if (!NotifyMessage4QuoteReceive.class.isInstance(message)
				&& !NotifyMessage4QuoteRes.class.isInstance(message)) {
			return;
		}

		IOpTypeEnum messageSubType = message.getHeader().getMessageSubType();
		QuoteBizTypeEnum quoteBizType = messageSubType.getQuoteBizType();
		OpBitEnum opbit = messageSubType.getOpBit();
		boolean isSuccess = false;
		boolean isSelfQuote = true;
		if (NotifyMessage4QuoteReceive.class.isInstance(message)) {
			isSelfQuote = ((NotifyMessage4QuoteReceive) message).isSelfQuote();
		}
		String errorCode = message.getHeader().getErrorCode();
		if ("0".equalsIgnoreCase(errorCode.trim())) {
			isSuccess = true;
		}
		QuoteOrder quoteOrder = null;
		WebDemoQuoteOrder webOrder = null;
		if (TypeUtils.isInstance(message.getPlayLoad(), QuoteOrder.class)) {
			quoteOrder = (QuoteOrder) message.getPlayLoad();
		}
		if (isSuccess && quoteOrder == null) {
			throw new RuntimeException("不确定的报价类型?序列号 = " + message.getHeader().getSerialNo());
		}
		if (isSuccess && StringUtils.isBlank(quoteOrder.getQuoteId())) {
			throw new RuntimeException("报价成功,但不存在报价编号?序列号 = " + message.getHeader().getSerialNo());
		}
		// 异步返回报价成功
		if (isSuccess && quoteOrder != null && StringUtils.isNotEmpty(quoteOrder.getQuoteId())) {
			webOrder = webDemoQuoteOrderService.getByQuoteId(quoteOrder.getQuoteId()).get(0);
			webOrder.setSuceess(true);
			webOrder.setQuoteBizType(getValue(quoteBizType.getValue()));
			webOrder.setOpbit(getValue(opbit.getValue()));
			webOrder.setSelfQuote(isSelfQuote);
		}
		// 异步返回报价失败
		if (!isSuccess) {
			if (quoteOrder != null && StringUtils.isNotEmpty(quoteOrder.getQuoteId())) {
				webOrder = webDemoQuoteOrderService.getByQuoteId(quoteOrder.getQuoteId()).get(0);
			}
			webOrder.setSuceess(false);
			webOrder.setQuoteBizType(getValue(quoteBizType.getValue()));
			webOrder.setOpbit(getValue(getValue(opbit.getValue())));
			webOrder.setSelfQuote(isSelfQuote);
		}
		if (webOrder != null) {
			System.out.println("======进行前台推送======" + webOrder);
			synchronized (synObj) {
				simpMessagingTemplate.convertAndSend("/topic/notfiyMessage", webOrder);
			}
			System.out.println("======前台推送完毕======");
		}
		System.out.println("--------------------------------------------------");
	}

	/**
	 * 根据指定的key的信息进行资源数据的读取控制
	 * 
	 * @param msgKey
	 *            表示要读取的资源文件的key的内容
	 * @param args
	 *            表示资源对应的内容
	 * @return
	 */
	public String getValue(String msgKey, Object... args) {
		return this.msgSource.getMessage(msgKey, args, Locale.getDefault());
	}

}
