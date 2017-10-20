package com.zzm.test.lambda;

public class Test1 {

	public static void main(String[] args) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("jdk1.8之前");
			}
			
		}).start();
		
		
		new Thread(() -> System.out.println("jdk1.8 使用Lanbda表达式")).start();
	}
	
}
