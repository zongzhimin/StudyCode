package com.zzm.test;

public class Test2 {
	public static void main(String[] args) {
		long startTime1 = System.currentTimeMillis();
		for(int i=0;i<100000;i++) {
			int a = i>>1;
		}
		long endTime1 = System.currentTimeMillis();
		
		long startTime2 = System.currentTimeMillis();
		for(int i=0;i<100000;i++) {
			int b = i/2;
		}
		long endTime2 = System.currentTimeMillis();
		System.out.println(startTime1);
		System.out.println(endTime1);
		System.out.println(startTime1-endTime1);
		System.out.println(startTime2);
		System.out.println(endTime2);
		System.out.println(startTime2-endTime2);
	}
}
