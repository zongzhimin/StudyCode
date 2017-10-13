package com.zzm.test.vast.service;

import java.util.ArrayList;
import java.util.Set;

public interface ShipSocketService {
	
	public ArrayList<String> getShips();

	public void startServer(int port);

	public void closeServer();

	public void stopServer();

	public void recoverServer();

	public void addShip(String shipName, String address, String port);

	public void send(String shipName, String message);

	public void clearShip();

	public String[] getStatus();
}
