package com.zzm.test.service;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {
	@Autowired
	private JmsTemplate jmsTemplate;
	
	public void sendMessage(Destination destination,String msg) {
		System.out.println("�����"+destination.toString()+"������Ϣ��"+msg);
		jmsTemplate.send(destination,new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(msg);
			}
			
		});
		
	}
	
	public void sendMessage(final String msg) {
        String destination =  jmsTemplate.getDefaultDestination().toString();
        System.out.println("�����" +destination+ "��������Ϣ------------" + msg);
        jmsTemplate.send(new MessageCreator() {
          public Message createMessage(Session session) throws JMSException {
            return session.createTextMessage(msg);
          }
        });
     
      }
	
	
}
