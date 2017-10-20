package com.zzm.test.newInterface;

public interface TestInterface {
	int getNum();
	
	default String getName() {
		return "zzm";
	}
}
