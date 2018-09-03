package com.xquant.platform.component.darren.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class WebDemoQuoteOrder implements Serializable {

	private boolean isSuceess;
	private String quoteBizType;
	private String opbit;
	private boolean isSelfQuote;
	private String clordidClientId;
	private String ordId;
	private String quoteType;
	private String price;
	private String volume;
	private String quoteId;
	private String transactTime;
	private String side;
	private String clrOrdId;
	private String ordstatus;
	
	public boolean isSuceess() {
		return isSuceess;
	}

	public void setSuceess(boolean isSuceess) {
		this.isSuceess = isSuceess;
	}
	
	public boolean isSelfQuote() {
		return isSelfQuote;
	}

	public void setSelfQuote(boolean isSelfQuote) {
		this.isSelfQuote = isSelfQuote;
	}

	public String getQuoteBizType() {
		return quoteBizType;
	}

	public void setQuoteBizType(String quoteBizType) {
		this.quoteBizType = quoteBizType;
	}

	public String getOpbit() {
		return opbit;
	}

	public void setOpbit(String opbit) {
		this.opbit = opbit;
	}

	public String getClordidClientId() {
		return clordidClientId;
	}

	public void setClordidClientId(String clordidClientId) {
		this.clordidClientId = clordidClientId;
	}

	public String getOrdId() {
		return ordId;
	}

	public void setOrdId(String ordId) {
		this.ordId = ordId;
	}

	public String getQuoteType() {
		return quoteType;
	}

	public void setQuoteType(String quoteType) {
		this.quoteType = quoteType;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getQuoteId() {
		return quoteId;
	}

	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
	}

	public String getTransactTime() {
		return transactTime;
	}

	public void setTransactTime(String transactTime) {
		this.transactTime = transactTime;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public String getClrOrdId() {
		return clrOrdId;
	}

	public void setClrOrdId(String clrOrdId) {
		this.clrOrdId = clrOrdId;
	}

	public String getOrdstatus() {
		return ordstatus;
	}

	public void setOrdstatus(String ordstatus) {
		this.ordstatus = ordstatus;
	}

	@Override
	public String toString() {
		return "WebDemoQuoteOrder [isSuceess=" + isSuceess + ", quoteBizType=" + quoteBizType + ", opbit=" + opbit
				+ ", isSelfQuote=" + isSelfQuote + ", clordidClientId=" + clordidClientId + ", ordId=" + ordId
				+ ", quoteType=" + quoteType + ", price=" + price + ", volume=" + volume + ", quoteId=" + quoteId
				+ ", transactTime=" + transactTime + ", side=" + side + ", clrOrdId=" + clrOrdId + ", ordstatus="
				+ ordstatus + "]";
	}
	
	
	public static void main(String[] args) {
		Class<?>[] interfaces = WebDemoQuoteOrder.class.getInterfaces();
		for (Class<?> class1 : interfaces) {
			System.out.println(class1.getSimpleName());
		}
	}

}
