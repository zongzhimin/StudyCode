package com.zzm.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zzm.test.entity.Account;
import com.zzm.test.service.impl.AccountService;

public class Main {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-cache-anno.xml");
		AccountService s = (AccountService) context.getBean("accountServiceBean");
		
		System.out.println("first query...");
		s.getAccountByName("zzm");
		System.out.println();
		
		System.out.println("secomdary query...");
		s.getAccountByName("zzm");
		System.out.println();
		
		System.out.println("test clear...");
		Account zzm1 = s.getAccountByName("zzm1");
		Account zzm2 = s.getAccountByName("zzm2");
		System.out.println();
		System.out.println("update");
		System.out.println();
		zzm1.setId(8421);
		s.updateAcount(zzm1);
		System.out.println("get after update...");
		s.getAccountByName("zzm1");
		s.getAccountByName("zzm2");
		System.out.println("get again after update...");
		s.getAccountByName("zzm1");
		s.getAccountByName("zzm2");
		
		System.out.println();
		
		System.out.println("test clear...");
		s.reload();
		System.out.println("get after reload");
		s.getAccountByName("zzm1");
		s.getAccountByName("zzm2");
		System.out.println("get again after reload..");
		s.getAccountByName("zzm1");
		s.getAccountByName("zzm2");
		
	}

}
