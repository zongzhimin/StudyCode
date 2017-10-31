package com.sealine;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * MQ点对点生产者
 * 
 * @author LIUWEI
 *
 */
public class QueuePublisher
{
    
    private final static String activeMQ_ip = "tcp://58.213.107.235:51616";
    
    private final static String username = "admin"; // 默认的连接用户名
    
    private final static String passward = "keydiea@123"; // 默认的连接密码
    
    /**
     * 发送点对点消息
     * 
     * @param msg
     * @param activeMQ_ip
     * @param queueName
     * @throws JMSException
     */
    public static void msgSend(String msg, String queueName)
        throws JMSException
    {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(username, passward, activeMQ_ip);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(queueName);
        MessageProducer producer = session.createProducer(queue);
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        
        TextMessage message = session.createTextMessage();
        message.setText(msg);
        producer.send(message);
        
        session.close();
        connection.stop();
        connection.close();
    }
}
