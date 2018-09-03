package com.darren.beanpostprocesser;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionVisitor;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.util.StringValueResolver;

public class ObscenityRemovingBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	private Set<String> obscenties;
	
	public ObscenityRemovingBeanFactoryPostProcessor() {
		this.obscenties = new HashSet<>();
	}
		
	public void setObscenties(Set<String> obscenties) {
		this.obscenties.clear();
		for (String obscentity : obscenties) {
			this.obscenties.add(obscentity.toUpperCase());
		}
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String[] beanNames = beanFactory.getBeanDefinitionNames();
        for (String beanName : beanNames) {
        	BeanDefinition bd = beanFactory.getBeanDefinition(beanName);
        	StringValueResolver valueResolver = new StringValueResolver() {
				
				@Override
				public String resolveStringValue(String strVal) {
					if(isObscene(strVal)) return "*********";
					return strVal;
				}
			};
			BeanDefinitionVisitor visitor = new BeanDefinitionVisitor(valueResolver);
			visitor.visitBeanDefinition(bd);
		}
	}

	public boolean isObscene(Object strVal) {
		String potentialObscenity = strVal.toString().toUpperCase();
		return this.obscenties.contains(potentialObscenity);
	}

}
