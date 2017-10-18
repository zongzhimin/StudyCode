package com.zzm.test.aopstudy.two;

public class Gaikun implements Player {

	@Override
	public void useQ() {
		System.out.println("盖伦使用Q技能");
	}

	@Override
	public void useW() {
		System.out.println("盖伦使用W技能");
	}

	@Override
	public void useE() {
		System.out.println("盖伦使用E技能");
	}

	@Override
	public void useR() {
		System.out.println("盖伦使用R技能");
	}

}
