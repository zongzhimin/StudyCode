package com.zzm.test.controller;

import java.util.Date;

import javax.xml.ws.RespectBinding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zzm.test.mail.Config;
import com.zzm.test.mail.Email;
import com.zzm.test.mail.Email.ContentType;
import com.zzm.test.mail.MailManager;

@RequestMapping("test")
@Controller
public class TestController {
//	@Autowired
//	@Qualifier("config")
//	private Config config;
//	 
//	@RequestMapping("mail")
//	@RespectBinding
//	public Object testMail(){
//		 MailManager manager = new MailManager(config);
//	        Email mail = new Email();
//	        mail.setSender("zzm");
//	        String[] address = {"1240236487@qq.com"};
//	        mail.setAddressees(address);
//	        mail.setSubject("测试");
//	        // mail.setContent("hi,船舶账单已出");
//	        mail.setContent("hi,123123");
//	        mail.setContentType(ContentType.HTML);
//	       // mail.setAffixName(new String[] {billPath});
//	        manager.send(mail);
//		
//		
//		return "end";
//	}
}
