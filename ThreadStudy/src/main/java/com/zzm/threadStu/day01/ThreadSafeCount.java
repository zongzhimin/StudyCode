package com.zzm.threadStu.day01;

public class ThreadSafeCount {
	private int counter = 0;
	
	public void increment(){
		synchronized (this) {
			counter++;
		}
	}
	public int get(){
		synchronized (this) {
			return counter;
		}
	}
	public static void main(String[] args) {
		
	}

}
