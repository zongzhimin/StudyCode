package com.sealine.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
/**
 * UDP接受船端信息服务
 * @author zongzhimin
 *
 */
@Service("udpServiceSocket")
public class UdpServiceSocket {

	private  DatagramSocket socket ;
	
	private Map<String,String[]> ships = new ConcurrentHashMap<String,String[]>();//存放接收到船端的地址端口号
	
	private boolean flag = false;//是否在循环接受船端信息 true-是，flase-否
	
	Logger logger = LoggerFactory.getLogger(UdpServiceSocket.class);

	public void setSocket(DatagramSocket socket) {
		logger.info("UdpServiceSocket 设定 DatagramSocket ："+socket+"........");
		this.socket = socket;
	}
	
	public UdpServiceSocket() {
	}
	
	public UdpServiceSocket(int port) {
		try {
			socket = new DatagramSocket(port);
		} catch (SocketException e) {
			logger.error("socket初始化失败。。。",e);
		}
	}
	
	public Map<String, String[]> getShips() {
		return ships;
	}

	public void setShips(Map<String, String[]> ships) {
		this.ships = ships;
	}
	
	public void receive(){
		logger.info("开始开启socket接受服务接受船端信息。。。");
		if(null==socket){
			logger.error("socket未初始化,开启接受信息失败。。。");
			return;
		}
		flag = true;
		new Thread(){
			@Override
			public void run() {
				byte[] buf =new byte[1024];
				DatagramPacket p = new DatagramPacket(buf,buf.length);
				try {
					logger.info("开始接收船端信息。。。");
					while(flag){
						socket.receive(p);
						String port = String.valueOf(p.getPort());//端口号
						String address = p.getAddress().toString();//船端地址
						if(address.startsWith("/")){
							address = address.substring(1);
						}
						String shipName = new String(p.getData()).trim();//船名
					//	if(null==ships.get(shipName)){
							ships.put(shipName, new String[]{address,port});
					//	}
						logger.info("接收到一条船端信息。。。"+shipName+" "+ships.get(shipName)[0]+" "+ships.get(shipName)[1]);
					}
				} catch (IOException e) {
					logger.error("接受船端信息异常(可能由close关闭引起)",e);
				}
			}
		}.start();
	}
	
	public void send(String shipName,String message){
		if(null==socket){
			logger.error("socket没有初始化，发送信息失败。。。");
			return;
		}
		new Thread(){
			@Override
			public void run() {
				try{
					logger.info("开始发送信息到船端。。。");
					logger.info("查询目标船只:"+shipName+"的信息。。。");
					String[] ship = ships.get(shipName);
					String address = null;
					String port = null;
					if(null!=ship&&ship.length==2){
						address = ship[0];
						port = ship[1];
					}else{
						logger.info("查询无该船ip端口信息 ship："+ship+"发送终止");
						return;
					}
					DatagramPacket dp = new DatagramPacket(message.getBytes(), message.getBytes().length,
			                InetAddress.getByName(address), Integer.valueOf(port));
					socket.send(dp);
					logger.info("向船只"+shipName+"信息发送完毕。。。");
					return;
				}catch(Exception e){
					logger.error("发送信息到船只"+shipName+"失败，服务异常",e);
					return;
				}
			}
			
		}.start();
	}
	
	public void close(){
		logger.info("关闭接受船端信息服务。。。");
		stopReceive();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			logger.error("线程sleep异常"+e);
		}
		if(null!=socket){
			try{
				socket.close();
			}catch(Exception e){
				logger.info("关闭服务，receive可能报错",e);
			}
			ships.clear();
		}
		logger.info("关闭接受船端信息服务成功。。。");
	}
	
	public boolean isclosed(){
		if(null==socket){
			return true;
		}
		return socket.isClosed();
	}
	
	public void stopReceive(){//停止循环receive
		flag = false;
	}
	
	public void startReceive(){
		receive();
	}
	public boolean isreceive(){//是否循环receive信息中
		return flag;
	}
	/**
	 * 启动服务并开始接收
	 * @param port
	 */
	public void start(int port){
		try {
			socket = new DatagramSocket(port);
			receive();//开始收接
		} catch (SocketException e) {
			logger.error("初始化socket失败。。。",e);
		}
	}
	/**
	 * 手动添加船只
	 * @param shipName
	 * @param address
	 * @param port
	 */
	public void addShip(String shipName,String address,String port){
		ships.put(shipName, new String[]{address,port});
	}
	public void clearShips(){
		ships.clear();
	}
	public String getPort(){
		if(socket==null){
			return "";
		}
		return String.valueOf(socket.getLocalPort());
	}
	public static void main(String[] args) throws InterruptedException{
		UdpServiceSocket s = new UdpServiceSocket();
		s.start(3344);
//		ships.put("hi", new String[]{"127.0.0.1","3344"});
//		s.setShips(ships);
		while(true){
			Thread.sleep(3000);
			s.send("a", "13123131");
			s.send("b", "2134234");
//			s.send("hi", "message from server");
		}
	}
}
