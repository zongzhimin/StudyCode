package com.zzm.threadStu2.day01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test5Lock {
	public static void main(String[] args) {
		C c = new C();
		new Thread() {

			@Override
			public void run() {
				c.methoda();
			}
			
		}.start();
		
		new Thread() {

			@Override
			public void run() {
				c.methoda();
			}
			
		}.start();
	}
}	
class C {
	Lock lock = new ReentrantLock();

	public void methoda() {
		System.out.println("进入C类methoda方法");
		lock.lock();
		System.out.println("进入加锁块");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		lock.unlock();
		System.out.println("C类methoda方法结束");
	}
}