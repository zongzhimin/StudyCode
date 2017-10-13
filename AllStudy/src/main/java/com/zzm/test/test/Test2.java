package com.zzm.test.test;

public class Test2
{
	public synchronized void say(){
		System.out.println("哈哈哈1");
		try
		{
			Thread.sleep(10000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		System.out.println("哈哈哈2");
	}
	
	public void say2(){
		System.out.println("Die,Die,Die");
	}
	
	public static void main(String[] args)
	{
		final Test2 t = new Test2();
		new Thread(){

			public void run()
			{
				System.out.println("Thread1..");
				t.say();
			}
			
		}.start();
		new Thread(){

			public void run()
			{
				System.out.println("Thread2..");
				t.say2();
			}
			
		}.start();
	}

}
