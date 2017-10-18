package com.zzm.test.aopstudy.three;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("aop.xml");
		HelloWorld hw1 = context.getBean("helloWorldImpl1",HelloWorld.class );
		HelloWorld hw2 = context.getBean("helloWorldImpl2",HelloWorld.class );
		
		hw1.printHelloWorld();
		System.out.println();
		hw1.doPrint();
		System.out.println();
		
		hw2.printHelloWorld();
		System.out.println();
		hw2.doPrint();
	}

}
