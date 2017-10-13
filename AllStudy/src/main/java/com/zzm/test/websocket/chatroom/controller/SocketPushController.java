package com.zzm.test.websocket.chatroom.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import com.zzm.test.websocket.controller.SocketController;
import com.zzm.test.websocket.service.MySocketHandle;

@Controller
@RequestMapping("socketPushController")
public class SocketPushController {
	 private static final Logger logger = LoggerFactory.getLogger(SocketController.class);
	  
	  @Autowired
	  private MySocketHandle mySocketHandler;
	  
	  @RequestMapping("login")
	  public String login(HttpSession session,String name){
	    logger.info(name+"登录了");
	    
	    session.setAttribute("user", name);
	    
	    return "../socketPush/chatroom";
	  }

	  @RequestMapping("sendMessage")
	  @ResponseBody
	  public String sendMessage(HttpSession session,String message){
		  String name = (String) session.getAttribute("user");
		  mySocketHandler.sendMessageToUsers( name,new TextMessage(name+" : "+message));
		  return "success";
	  }
}
