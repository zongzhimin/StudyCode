package com.zzm.test.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zzm.test.service.ConsumerService;
import com.zzm.test.service.ProducerService;

@Controller
public class MQController {
	
	@Autowired
	private Destination demoQueueDestination;
	
	@Autowired
	private Destination demoQueueDestination2;
	
	@Autowired
	private ProducerService producer;
	
	@Autowired
	private ConsumerService consumer;
	
	@RequestMapping(value="/producer",method=RequestMethod.GET)
    public ModelAndView producer(){
        System.out.println("------------go producer");
        
        Date now = new Date(); 
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format( now ); 
        System.out.println(time);
        
        ModelAndView mv = new ModelAndView();
        mv.addObject("time", time);
        mv.setViewName("jms_producer");        
        return mv;
    }
	
	@RequestMapping(value="/onsend",method=RequestMethod.POST)
    public ModelAndView producer(@RequestParam("message") String message) {
        System.out.println("------------send to jms");
        ModelAndView mv = new ModelAndView();
        producer.sendMessage(demoQueueDestination, message);
        mv.setViewName("welcome");
        return mv;
    }
	
	@RequestMapping(value="/receive",method=RequestMethod.GET)
    public ModelAndView queue_receive() throws JMSException {
        System.out.println("------------receive message");
        ModelAndView mv = new ModelAndView();
        
        TextMessage tm = consumer.receive(demoQueueDestination);
        mv.addObject("textMessage", tm.getText());
        
        mv.setViewName("queue_receive");
        return mv;
    }
	
	@RequestMapping(value="/receive2",method=RequestMethod.GET)
    public ModelAndView queue_receive2() throws JMSException {
        System.out.println("------------receive message");
        ModelAndView mv = new ModelAndView();
        
        TextMessage tm = consumer.receive(demoQueueDestination2);
        mv.addObject("textMessage", tm.getText());
        
        mv.setViewName("queue_receive");
        return mv;
    }
	
	@RequestMapping(value="/jms",method=RequestMethod.GET)
    public ModelAndView jmsManager() throws IOException {
        System.out.println("------------jms manager");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("welcome");
        
        JMXServiceURL url = new JMXServiceURL("");
        JMXConnector connector = JMXConnectorFactory.connect(url);
        connector.connect();
        MBeanServerConnection connection = connector.getMBeanServerConnection();
        
        return mv;
    }
	
	
}
