package com.darren.Aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopMain {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		Horseman hm = (Horseman) ctx.getBean("horseManProxyFactory");
		Swordman sm = (Swordman) ctx.getBean("swordManProxyFactory");
		hm.rush("Ghoul");
		hm.chop("Ghoul");
		sm.block("Ghoul");
		sm.chop("Ghoul");
		hm.rush("Abomination");
		hm.chop("Abomination");
		sm.block("Abomination");
		sm.chop("Abomination");
		((ConfigurableApplicationContext) ctx).close();
	}
}
