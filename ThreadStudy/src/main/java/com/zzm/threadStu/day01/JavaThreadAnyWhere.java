package com.zzm.threadStu.day01;

public class JavaThreadAnyWhere {

	public static void main(String[] args) {
		System.out.println("Main Method was executed by thread:"+Thread.currentThread().getName());
		Helper h = new Helper("Zzm");
		//h.run();//依然是Main线程
		Thread t = new Thread(h);
		t.setName("New Thread");
		t.start();
	}
	
	static class Helper implements Runnable{
		private final String message;
		
		public Helper(String message) {
			super();
			this.message = message;
		}
		private void doSomeThing(String message){
			System.out.println("doSomeThing方法由"+Thread.currentThread().getName()+"线程调用。");
			System.out.println("doSomeThing with "+message);
		}
		public void run() {
			doSomeThing(message);
		}
		
	}
}
