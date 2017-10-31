package com.zzm.test;

import java.io.UnsupportedEncodingException;

public class ByteTest {

	public static void main(String[] args) {
		System.out.println("你好".getBytes());
		
		System.out.println(0x000f);
		System.out.println(Integer.toBinaryString(2));

		System.out.println(Integer.toBinaryString(-2));
		byte[] bytes;
		try {
			bytes = "你好".getBytes("UTF-8");
			System.out.println(bytes);
			for(byte s : bytes){
				System.out.println(s);
				System.out.println(Integer.toHexString(s));
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

}
