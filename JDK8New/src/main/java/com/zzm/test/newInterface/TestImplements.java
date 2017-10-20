package com.zzm.test.newInterface;

public class TestImplements implements TestInterface {

	@Override
	public int getNum() {
		return 100;
	}
	
//	public String getName() {
//		return "zongzhimin";
//	}
	
	
	public static void main(String[] args) {
		TestImplements t = new TestImplements();
		System.out.println(t.getName());
	}
}
