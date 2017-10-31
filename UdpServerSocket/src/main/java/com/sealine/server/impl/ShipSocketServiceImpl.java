package com.sealine.server.impl;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sealine.server.ShipSocketService;
import com.sealine.socket.UdpServiceSocket;


@Service("shipSocketService")
public class ShipSocketServiceImpl implements ShipSocketService {
	
	@Autowired
	private UdpServiceSocket dpServiceSocket;

	@Override
	public ArrayList<String> getShips() {
		Map<String,String[]> ships = dpServiceSocket.getShips();
		Set<String> shipNames = ships.keySet();
		return new ArrayList<String>(shipNames);
	}

	@Override
	public void startServer(int port) {
		dpServiceSocket.start(port);
	}

	@Override
	public void closeServer() {
		dpServiceSocket.close();
	}

	@Override
	public void stopServer() {
		dpServiceSocket.stopReceive();
	}

	@Override
	public void recoverServer() {
		dpServiceSocket.startReceive();
	}

	@Override
	public void addShip(String shipName, String address, String port) {
		dpServiceSocket.addShip(shipName, address, port);
	}

	@Override
	public void send(String shipName, String message) {
		dpServiceSocket.send(shipName, message);
	}

	@Override
	public void clearShip() {
		dpServiceSocket.clearShips();
	}

	@Override
	public String[] getStatus() {
		int close = 0;//开启 ：1，关闭 ：0
		int receive =0;//运行：2，暂停：0
		if(!dpServiceSocket.isclosed()){//开启
			close =1;
			if(dpServiceSocket.isreceive()){
				receive=2;
			}
		}
		String port = dpServiceSocket.getPort();
		return new String[]{String.valueOf(close+receive),port};//0:关闭 1-暂停 3-开启
	}
	
	
}
