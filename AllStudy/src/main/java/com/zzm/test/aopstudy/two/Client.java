package com.zzm.test.aopstudy.two;

public class Client {

	public static void main(String[] args) {
		MyProxy hander = new MyProxy();
		Player gailun =(Player) hander.createProxyInstance(new Gaikun());
		gailun.useQ();
		
		gailun.useR();
	}

}
