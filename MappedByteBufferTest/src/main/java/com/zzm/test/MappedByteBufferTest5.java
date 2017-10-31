package com.zzm.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MappedByteBufferTest5 {
	
	public static void main(String[] args) {
		try {
			FileInputStream fis = new FileInputStream("E:"+File.separator+"test"+File.separator+"123.txt");
			FileOutputStream fos = new FileOutputStream("E:"+File.separator+"test1"+File.separator+"123.txt");
			FileChannel fc = fis.getChannel();
			MappedByteBuffer mbb = fis.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, fc.size());	
			byte[] bytes = new byte[mbb.limit()];
			ByteBuffer bb = mbb.get(bytes);
			fos.write(bytes);
			fis.close();
			fc.close();
			fos.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
