package com.zzm.test.aopstudy.three;

public class HelloWorldImpl2 implements HelloWorld {

	@Override
	public void printHelloWorld() {
		System.out.println("Enter HelloWorldImpl2.printHelloWorld()");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doPrint() {
		System.out.println("Enter HelloWorldImpl2.doPrint()");
	}

}
