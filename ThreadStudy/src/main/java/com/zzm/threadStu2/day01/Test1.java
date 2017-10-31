package com.zzm.threadStu2.day01;

public class Test1 {

	public static void main(String[] args) {
		A ga = new A();
		new Thread() {

			@Override
			public void run() {
//				A a = new A();//不同的new出来的A对象之间不锁
				ga.methoda("Thread1");
			}
			
		}.start();
		new Thread() {

			@Override
			public void run() {
//				A a = new A();
				ga.methoda("Thread2");
			}
			
		}.start();
		
	}

}

class A {
	Object obj = new Object();
	public void methoda(String name) {
		System.out.println(name+"进入A类方法methoda。。。");
		synchronized (obj) {
			System.out.println(name+"进入同步块");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(name + "A类方法执行执行完毕");
	}
}