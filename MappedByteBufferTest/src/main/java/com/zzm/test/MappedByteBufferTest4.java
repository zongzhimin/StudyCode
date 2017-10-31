package com.zzm.test;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MappedByteBufferTest4 {
	
	public static void main(String[] args) {
		try {
			FileInputStream fis = new FileInputStream("E:"+File.separator+"test"+File.separator+"redis-4.0.1.tar.gz");
			FileChannel fc = fis.getChannel();
			MappedByteBuffer mbb = fis.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, fc.size());	
			fis.close();
			fc.close();
			System.out.println(mbb.capacity());
			System.out.println(mbb.limit());
			System.out.println(mbb.position());
			//mbb.flip();
			System.out.println(mbb.hasArray());
			System.out.println(mbb.capacity());
			System.out.println(mbb.limit());
			System.out.println(mbb.position());
			byte[] bytes = new byte[mbb.limit()];
			ByteBuffer bb = mbb.get(bytes);
			
			
			System.out.println(new String(bytes,"GBK"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
