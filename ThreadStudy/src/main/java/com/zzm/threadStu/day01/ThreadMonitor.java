package com.zzm.threadStu.day01;

public class ThreadMonitor {

	public static void main(String[] args) {

		Thread t1 = new Thread(){
			@Override
			public void run() {
				for(int i =0;i<1000000000;i++){
					String s = "Boom"+i;
				}
			}
			
		};
		t1.setName("ZzmThread1");
		t1.start();
		
		Thread t2 = new Thread(){
			@Override
			public void run() {
				for(long i =0;i<1000000000000000l;i++){
					String s = "Boom"+i;
				}
			}
			
		};
		t2.setName("ZzmThread2");
		t2.start();
		
		Thread t3 = new Thread(){
			@Override
			public void run() {
				for(long i =0;i<1000000000l;i++){
					String s = "Boom"+i;
				}
			}
			
		};
		t3.setName("ZzmThread3");
		t3.start();
	}

}
