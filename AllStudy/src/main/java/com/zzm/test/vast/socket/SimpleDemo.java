package com.zzm.test.vast.socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SimpleDemo {
	
	public static void receive(){
		new Thread(){

			@Override
			public void run() {
				DatagramSocket socket =null;
				try {
					System.out.println("开启服务。。。");
					socket = new DatagramSocket(3344);
					byte[] buffer = new byte[1024];
					DatagramPacket p = new DatagramPacket(buffer, buffer.length);
					System.out.println("开始开启监听。。。");
		            socket.receive(p);//不断监听的话可以放到while循环中
		            byte[] data = new byte[p.getLength()];
		            int port = p.getPort();//对方端口号
		        	String address = p.getAddress().toString();//船端地址（多个/）
		        	String message= new String(p.getData()).trim();//信息    getData()取得报文的byte数组编码
		            System.out.println("Receiver：接收到来自"+address+"端口："+port+"的消息：" + message);
		            socket.close();
		            System.out.println("接收完毕关闭receive服务。。。。");
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					if(null!=socket){
						socket.close();
					}
				}
				
				
			}
			
		}.start();
	}
	
	public static void send(){
		new Thread(){

			@Override
			public void run() {
				DatagramSocket socket =null;
				try {
					socket = new DatagramSocket();
					String serverHost = "127.0.0.1";//目标地址是本地地址
		            int serverPort = 3344;//目标端口
		            DatagramPacket dp = new DatagramPacket(("你好 recevier").getBytes(), ("你好 recevier").getBytes().length,
		                InetAddress.getByName(serverHost), serverPort);
		            System.out.println("sender：发送消息。。。。");
		            socket.send(dp);//发送消息时随机选择端口
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					if(null!=socket){
						socket.close();
						System.out.println("发送完毕，关闭send服务");
					}
				}
	            
			}
			
		}.start();
	}
	
	public static void main(String[] args) {
		receive();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		send();
	}

}
