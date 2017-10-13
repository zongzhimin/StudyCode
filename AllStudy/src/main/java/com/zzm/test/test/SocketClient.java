package com.zzm.test.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {
	private Socket socket;  
    BufferedReader reader;  
    private PrintWriter writer;  
    
    public void start(){
    	getConnect();
        receive();
        send();
    }
    
    public void getConnect(){
    	//127.0.0.1表示本机IP，10000为服务器Socket设置的端口  
    	 try {
 			socket = new Socket("172.16.2.71", 10000);
 			reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));  
 	        writer = new PrintWriter(socket.getOutputStream(), true); 
 		}catch (Exception e) {
 			e.printStackTrace();
 		}  
    }
    
    public void receive(){
		new Thread(){

			@Override
			public void run() {
				while(true){
					try {
						if(null==reader){
							continue;
						}
						String line = null;
						while((line=reader.readLine())!=null){
							System.out.println(line);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
		}.start();
	}
	
    public void send(){
		new Thread(){
			@Override
			public void run() {
				Scanner s = new Scanner(System.in);
				while(true){
					if(null==writer){
						continue;
					}
					String message = s.nextLine();
					writer.println(message);
				}
			}
		}.start();
		
	}
    
	public void send(String message){
		writer.println(message);
	}
	
	public void close(){
		try{
			if(null!=reader){
				reader.close();
			}
			if(null!=writer){
				writer.close();
			}
			if(null!=socket){
				socket.close();
			}
		}catch(Exception e){
			
		}
		
	}
	
	public static void main(String[] args){
		SocketClient c = new SocketClient();
		c.start();
	}
}
