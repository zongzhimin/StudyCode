package com.zzm.test.websocket.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.socket.TextMessage;

import com.zzm.test.websocket.socket.SocketHandler;

@Controller
@RequestMapping("socketController")
public class SocketController {
	 private static final Logger logger = LoggerFactory.getLogger(SocketController.class);
	  
	  @Autowired
	  private SocketHandler socketHandler;
	  
	  @RequestMapping(value="/login")
	  public String login(HttpSession session){
	    logger.info("用户登录了建立连接啦");
	    
	    session.setAttribute("user", "zzm");
	    
	    return "../socketPush/home";
	  }

	  @RequestMapping(value = "/message", method = RequestMethod.GET)
	  public String sendMessage(){
	    
	    socketHandler.sendMessageToUser("zzm", new TextMessage("这是一条测试的消息"));
	    
	    return "../socketPush/message";
	  }
}
