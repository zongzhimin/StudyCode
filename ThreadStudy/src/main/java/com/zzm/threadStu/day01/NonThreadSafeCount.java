package com.zzm.threadStu.day01;

public class NonThreadSafeCount {
	private int counter = 0;
	
	public void increment(){
		counter++;
	}
	public int get(){
		return counter;
	}
	public static void main(String[] args) {
		
	}

}
