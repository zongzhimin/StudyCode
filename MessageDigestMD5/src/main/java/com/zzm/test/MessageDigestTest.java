package com.zzm.test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MessageDigestTest {

	public static void main(String[] args) {
		try {

			System.out.println(getHash("你好！","MD5"));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getHash(String source,String hashType){
		
		char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
		
		try {
			MessageDigest md = MessageDigest.getInstance(hashType);
			md.update(source.getBytes());
			byte[] encryptStr = md.digest();
			char str[] = new char[16*2];
			int k = 0 ;
			for(int i=0;i<16;i++){
				byte byte0 = encryptStr[i]; // 取第 i 个字节
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
