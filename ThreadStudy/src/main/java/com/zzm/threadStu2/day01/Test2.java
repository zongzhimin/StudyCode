package com.zzm.threadStu2.day01;

public class Test2 {

	public static void main(String[] args) {
		B b = new B();
		Object obj = new Object();
		new Thread() {

			@Override
			public void run() {
				b.methoda("Thread1",new Object());//不同的Obj不行
			}
			
		}.start();
		new Thread() {

			@Override
			public void run() {
				b.methoda("Thread2",new Object());
			}
			
		}.start();
	}

}

class B {
	Object obj = new Object();
	public void methoda(String name,Object obj2) {
		System.out.println(name+"进入B类方法methoda。。。");
		synchronized (obj2) {
			System.out.println(name+"进入同步块");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(name + "B类方法执行执行完毕");
	}
}