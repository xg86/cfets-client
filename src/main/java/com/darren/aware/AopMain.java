package com.darren.aware;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopMain {

	public static void main(String[] args) {
		 ApplicationContext ctx = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		 ((ConfigurableApplicationContext)ctx).close();
	}
}
