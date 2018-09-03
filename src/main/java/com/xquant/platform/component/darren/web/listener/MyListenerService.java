package com.xquant.platform.component.darren.web.listener;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.SmartLifecycle;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.xquant.platform.component.cfets.facade.notify.FacadeNotifyService;
import com.xquant.platform.component.darren.inner.service.WebDemoQuoteOrderService;

@Component
public class MyListenerService implements SmartLifecycle, InitializingBean {

	@Resource
	private FacadeNotifyService facadeNotifyService;

	@Resource
	private SimpMessagingTemplate simpMessagingTemplate;

	@Autowired
	private WebDemoQuoteOrderService webDemoQuoteOrderService;

	@Autowired
	private MessageSource msgSource;

	private MyNotifyListener listner = null;

	private boolean isrunning;

	@Override
	public void start() {
		isrunning = true;
		listner = new MyNotifyListener(msgSource, simpMessagingTemplate,webDemoQuoteOrderService);
		System.out.println("=============添加监听器成功================");
//		facadeNotifyService.resetAllListeners();
//		facadeNotifyService.removeListener(listner);
		facadeNotifyService.addListener(listner);
	}

	@Override
	public void stop() {
		if (listner != null) {
			facadeNotifyService.removeListener(listner);
		}
	}

	@Override
	public boolean isRunning() {
		return isrunning;
	}

	@Override
	public int getPhase() {
		return 0;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(simpMessagingTemplate, "simpMessagingTemplate can not be null");
		Assert.notNull(webDemoQuoteOrderService, "webDemoQuoteOrderService can not be null");
		Assert.notNull(msgSource, "msgSource can not be null");
		Assert.notNull(facadeNotifyService, "facadeNotifyService can not be null");
	}

	@Override
	public boolean isAutoStartup() {
		return true;
	}

	@Override
	public void stop(Runnable callback) {
		isrunning = false;
	}

}
