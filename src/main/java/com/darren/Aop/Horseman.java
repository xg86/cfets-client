package com.darren.Aop;

public class Horseman {
	public void rush(String enemy) {
		System.out.println(this.getClass().getSimpleName() + "冲刺攻击" + enemy);
	}

	public void chop(String enemy) {
		System.out.println(this.getClass().getSimpleName() + "砍劈攻击" + enemy);
	}
}