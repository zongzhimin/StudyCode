package com.zzm.test.annotation;

import com.zzm.test.annotation.FruitColor.Color;

public class Apple {
	
	@FruitName("apple")
	private String appleName;
	
	@FruitColor(fruitColor=Color.RED)
	private String appleColor;
	
	@FruitProvider(id=1,name="苹果公司",address="南京咬一口公司")
	private String appleProvider;
	
	public Apple() {
	}

	public static void main(String[] args) {

	}

	public String getAppleName() {
		return appleName;
	}

	public void setAppleName(String appleName) {
		this.appleName = appleName;
	}

	public String getAppleColor() {
		return appleColor;
	}

	public void setAppleColor(String appleColor) {
		this.appleColor = appleColor;
	}

	public String getAppleProvider() {
		return appleProvider;
	}

	public void setAppleProvider(String appleProvider) {
		this.appleProvider = appleProvider;
	}
	
	
}
