package com.zzm.test.service;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
	
	@Autowired
    private JmsTemplate jmsTemplate;
	
	public TextMessage receive(Destination destination) {
	    TextMessage tm = (TextMessage) jmsTemplate.receive(destination);
	    try {
	            System.out.println("�Ӷ���" + destination.toString() + "�յ�����Ϣ��\t"
	                + tm.getText());
	    } catch (JMSException e) {
	        e.printStackTrace();
	    }
	    
	    return tm;
	    
	}
	
}
