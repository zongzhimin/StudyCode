package com.zzm.test.aopstudy.one;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zzm.test.aopstudy.one.service.PurchaseService;

public class Test {
	
	@org.junit.Test
	public void test() {
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-aop.xml");  
        PurchaseService service=(PurchaseService) context.getBean("purchaseService");  
        service.purchaseProduct("电风扇", 98, "日用品");  
	}

}
