package cn.springmvc.demo.activemq.producer;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.*;

/**
 * Created by Vincent Wang(王言斌)) on 2017/3/4 0004.
 * Version 1.0.0
 * Description This is a demo Producer Service achieved by ActiveMQ
 */
@Service
public class ProducerService {

    @Resource(name="jmsQueueTemplate")
    private JmsTemplate jmsQueueTemplate;

    @Resource(name="jmsTopicTemplate")
    private JmsTemplate jmsTopicTemplate;

    /**
     * 向指定队列发送消息
     */
    public void sendMessage(Destination destination, final String msg) {
        System.out.println("向队列" + destination.toString() + "发送了消息------------" + msg);
        jmsQueueTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        });
    }

    /**
     * 向默认队列发送消息
     */
    public void sendMessage(final String msg) {
        String destination =  jmsQueueTemplate.getDefaultDestination().toString();
        System.out.println("向队列" + destination + "发送了消息------------" + msg);
        jmsQueueTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        });

    }

    /**
     * 向订阅频道发送消息
     */
    public void sendTopicMessage(Destination destination, final String msg) {
        System.out.println("向主题：" + destination.toString()+ "发送了消息------------" + msg);
        jmsTopicTemplate.sendAndReceive(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        });
    }

    /**
     * 向订阅频道发送消息
     */
    public void sendTopicMessage(final String msg) {
        String destination =  jmsTopicTemplate.getDefaultDestination().toString();
        System.out.println("向队列"  + destination + "发送了消息------------" + msg);
        jmsTopicTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        });

    }
}
