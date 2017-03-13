package cn.springmvc.demo.activemq.listener;

import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * Created by Vincent on 2017/3/7.
 * \Description 消息队列监听器 需添加到容器管理或保留类上注解
 */
@Component
@EnableJms
public class JmsMsgListener {

    //当收到消息后，自动调用该方法,spring配置默认监听器，负责接收消息
    @JmsListener(containerFactory="jmsListenerContainerFactory",destination = "ssmactivemq")
    public void onMessage(Message message) {
        TextMessage tm = (TextMessage) message;
        try {
            System.out.println("JMS消息监听器监听到如下消息内容：\n" + tm.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
