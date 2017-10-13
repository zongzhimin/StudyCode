package com.zzm.test.mail;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailDemo {

	public static void main(String[] args) throws AddressException, MessagingException {
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.auth", "true");//设置访问smtp服务器需要认证
		properties.setProperty("mail.transport.protocol", "smtp"); //设置访问服务器的协议
		properties.setProperty("mail.smtp.ssl.enable", "true");

		Session session = Session.getDefaultInstance(properties);
		session.setDebug(true); //打开debug功能
		
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("564755645@qq.com")); //设置发件人
		msg.setText("send mail test"); //设置邮件内容
		msg.setSubject("mailTest"); //设置邮件主题
		
		Transport trans = session.getTransport();
		trans.connect("smtp.qq.com", 465, "564755645", "tediwstjvxxybfdj"); //连接邮箱smtp服务器
		trans.sendMessage(msg, new Address[]{new InternetAddress("1240236487@qq.com")}); //发送邮件
		
		trans.close(); //关闭连接
	}

}
