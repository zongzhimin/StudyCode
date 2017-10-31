package com.sealine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sealine.server.ShipSocketService;


@Controller
@RequestMapping("shipSocket")
public class ShipSocketController {
	
	@Autowired
	private ShipSocketService shipSocketService;
	
	@ResponseBody
	@RequestMapping("getShips")
	public Object getShips(){
		
		return shipSocketService.getShips();
		
	}
	
	@ResponseBody
	@RequestMapping("shipServer")
	public Object shipServer(Integer port,Integer kind){
		switch(kind){
			case 1 : shipSocketService.startServer(port);break;
			case 2 : shipSocketService.closeServer();break;
			case 3 : shipSocketService.stopServer();break;
			case 4 : shipSocketService.recoverServer();break;
		}
		return "success";
		
	}
	
	@ResponseBody
	@RequestMapping("addShip")
	public Object addShip(String shipName,String address,String port){
		shipSocketService.addShip(shipName,address,port);
		
		return "success";
	}
	
	@ResponseBody
	@RequestMapping("clearShip")
	public Object clearShip(){
		shipSocketService.clearShip();
		
		return "success";
	}
	
	@ResponseBody
	@RequestMapping("getStatus")
	public Object getStatus(){
		return shipSocketService.getStatus();
	}
	@ResponseBody
	@RequestMapping("send")
	public Object send(String shipName,String message){
		shipSocketService.send(shipName,message);
		
		return "success";
	}
}
