package com.zzm.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class MappedByteBufferTest {

	public static void main(String[] args) {
		
		ByteBuffer buff = ByteBuffer.allocate(128);
		FileChannel fin = null;
        FileChannel fout = null;
		
       
        try{
        	fin = new FileInputStream("E:"+File.separator+"test"+File.separator+"redis-4.0.1.tar.gz").getChannel();
			fout = new FileOutputStream("E:"+File.separator+"test1"+File.separator+"redis-4.0.1.tar.gz").getChannel();
			
			while(fin.read(buff) != -1) {
                buff.flip();
                fout.write(buff);
                buff.clear();
            }
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
            try {
                if(fin != null) {
                    fin.close();
                }
                if(fout != null) {
                    fout.close();
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
		
	}

}
