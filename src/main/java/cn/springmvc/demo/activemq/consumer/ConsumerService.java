package cn.springmvc.demo.activemq.consumer;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by Vincent Wang(王言斌)) on 2017/3/4 0004.
 * Version 1.0.0
 * Description This is a demo Consumer Service achieved by ActiveMQ
 */
@Service
public class  ConsumerService {

    @Resource(name="jmsQueueTemplate")
    private JmsTemplate jmsQueueTemplate;

    @Resource(name="jmsTopicTemplate")
    private JmsTemplate jmsTopicTemplate;

    /**
     * 接收消息
     */
    public TextMessage receive(Destination destination) {
        TextMessage tm = (TextMessage) jmsQueueTemplate.receive(destination);
        try {
            System.out.println("从队列" + destination.toString() + "收到了消息：\t"
                    + tm.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return tm;
    }

    /**
     * 订阅主题，接收消息
     */
    public TextMessage receiveFromTopic(Destination destination) {
        TextMessage tm = (TextMessage) jmsTopicTemplate.receive(destination);
        try {
            System.out.println("从队列" + destination.toString() + "收到了消息：\t"
                    + tm.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return tm;
    }

}
