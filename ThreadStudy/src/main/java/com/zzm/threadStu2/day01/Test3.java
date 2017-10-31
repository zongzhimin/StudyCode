package com.zzm.threadStu2.day01;

public class Test3 extends Thread{
	 private String lock ;  
	    private String name;  
	      
	    public Test3(String name,String lock){  
	        this.name = name;  
	        this.lock = lock;  
	    }  
	      
	    @Override  
	    public void run() {  
	        synchronized (lock) {  
	            for(int i = 0 ; i < 3 ; i++){  
	                System.out.println(name + " run......");  
	            }  
	        }  
	    }  
	      
	    public static void main(String[] args) {  
	        String lock  = new String("test");  
	        for(int i = 0 ; i < 5 ; i++){  
	            new Test3("ThreadTest_" + i,lock).start();  
	        }  
	    }  
}
