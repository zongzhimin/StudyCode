package com.zzm.test.websocket.service;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

@Service
public class MySocketHandle implements WebSocketHandler{
	private Logger logger = LoggerFactory.getLogger(MySocketHandle.class);
	private Map<String,WebSocketSession> users = new ConcurrentHashMap<String,WebSocketSession>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		logger.info("建立socket连接");
		String userName = session.getAttributes().get("user").toString();
		if(null!=userName&&!"".equals(userName)){
			users.put(userName, session);
			session.sendMessage(new TextMessage("system："+userName+"连接成功。。。"));
		}
	}

	@Override
	public void handleMessage(WebSocketSession session,
			WebSocketMessage<?> message) throws Exception {
		
	}

	@Override
	public void handleTransportError(WebSocketSession session,
			Throwable exception) throws Exception {
		if(session.isOpen()){
			session.close();
		}
		logger.error("连接出现错误",exception);
		users.remove(session.getAttributes().get("user").toString());
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session,
			CloseStatus closeStatus) throws Exception {
		logger.info("连接关闭");
		users.remove(session.getAttributes().get("user").toString());
	}

	@Override
	public boolean supportsPartialMessages() {

		return false;
	}
	
	public void sendMessageToUsers(String sender,TextMessage message){
		Set<Map.Entry<String, WebSocketSession>> entrySet = users.entrySet();
		for(Map.Entry<String, WebSocketSession> entry : entrySet){
			String userName = "";
			try{
			userName = entry.getKey();
			if(userName==null||userName.equals(sender)){
				continue;
			}
			WebSocketSession session = entry.getValue();
			session.sendMessage(message);
			}catch(Exception e){
				logger.error("发送信息给"+userName+"失败",e);
			}
		}
	}
	
	public void sendMessageToUser(String userName,TextMessage message){
		try{
			WebSocketSession session = users.get(userName);
			session.sendMessage(message);
		}catch(Exception e){
			logger.error("发送消息给"+userName+"失败",e);
		}
		
	}
}
