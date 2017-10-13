package com.zzm.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zzm.test.service.impl.AccountService;

public class Main3 {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-cache-anno.xml");
		AccountService s = (AccountService) context.getBean("accountServiceBean");
		s.getAccount("zzm", "123456", true);
		s.getAccount("zzm", "123456", true);
		s.getAccount("zzm", "123456", false);
		s.getAccount("zzm", "654321", true);
		s.getAccount("zzm", "654321", true);
		
		
	}

}
