package com.zzm.test;

import java.io.File;
import java.io.FileInputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class MappedByteBufferTest2 {
	private final static Charset charset = Charset.forName("GBK"); 
	
	public static void main(String[] args) {
		try {
			FileInputStream fis = new FileInputStream("E:"+File.separator+"test"+File.separator+"123.txt");
			FileChannel fc = fis.getChannel();
			MappedByteBuffer mbb = fis.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, fc.size());	
			fis.close();
			fc.close();
			String str = charset.decode(mbb).toString();
			System.out.println(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
