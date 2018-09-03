package com.darren.Aop;

import java.lang.reflect.Method;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

public class DynamicStoragePointCut extends DynamicMethodMatcherPointcut {

	private int methodOption;
	private int classOption;

	public void setMethodOption(int methodOption) {
		this.methodOption = methodOption;
	}

	public void setClassOption(int classOption) {
		this.classOption = classOption;
	}
	
	// 对方法做动态的输入参数匹配检查
	@Override
	public boolean matches(Method method, Class<?> targetClass, Object... args) {
		return args[0].equals("Abomination");
	}

	@Override
	public boolean matches(Method method, Class<?> targetClass) {
		switch (methodOption) {
		case 1:
			return "chop".equals(method.getName());
		case 2:
			return "rush".equals(method.getName());
		default:
			return true;
		}
	}

	@Override
	public ClassFilter getClassFilter() {
		return new ClassFilter() {
			@Override
			public boolean matches(Class<?> cls) {
				switch (classOption) {
				case 1:
					return Horseman.class.isAssignableFrom(cls);
				case 2:
					return Swordman.class.isAssignableFrom(cls);
				default:
					return true;
				}
			}
		};
	}
}
