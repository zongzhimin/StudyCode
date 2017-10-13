package com.zzm.test.test;

import java.io.UnsupportedEncodingException;

public class CodeEndTest {

	public static void main(String[] args) {
//		byte[] buf = new byte[1024];
//		byte[] test1 = "test1".getBytes();
//		try {
//			System.out.println(new String(test1,"UTF-8"));
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		
		try {
			byte[] a = "test1".getBytes("UTF-8");
			System.out.println(new String(a,"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
