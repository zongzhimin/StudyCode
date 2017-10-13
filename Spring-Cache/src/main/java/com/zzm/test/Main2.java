package com.zzm.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zzm.test.service.impl.AccountService;

public class Main2 {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-cache-anno.xml");
		AccountService s = (AccountService) context.getBean("accountServiceBean");
		s.getAccountByName("zzm123423424");
		s.getAccountByName("zzm123423424");
		
		s.getAccountByName("zzm");
		s.getAccountByName("zzm");
	}

}
