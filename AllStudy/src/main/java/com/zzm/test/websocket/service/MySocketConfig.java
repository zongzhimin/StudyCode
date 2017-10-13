package com.zzm.test.websocket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.zzm.test.websocket.socket.WebSocketInterceptor;


@Configuration
@EnableWebMvc
@EnableWebSocket
public class MySocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer{
	
	@Autowired
	private MySocketHandle mySocketHandle;//自己的handler
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		//注册处理拦截器,拦截url为socketServer的请求
	    registry.addHandler(mySocketHandle, "/socketChatroom.do").addInterceptors(new WebSocketInterceptor());//拦截的请求，（注意首先得被servlet拦截到，即要注意web-inf0中的配置）
	}

}
