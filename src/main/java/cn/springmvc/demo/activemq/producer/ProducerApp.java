package cn.springmvc.demo.activemq.producer;

import com.alibaba.fastjson.JSONObject;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.UUID;

/**
 * Created by Vincent on 2017/3/7.
 * Description 生产者（Client）
 */
public class ProducerApp {

    public ProducerApp() {
        System.out.println();
    }

    public static void main(String[] args){
        ActiveMQConnectionFactory amqConnectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        try {
            //  !. 获取并启动连接
            Connection connection = amqConnectionFactory.createConnection();
            connection.start();
            //  2、创建会话和队列
            Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("ssmactivemq");
            //  3、创建生产者
            MessageProducer producer = session.createProducer(destination);
            //  4、生产消息
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("type", "send");
            jsonObject.put("id", "1");
            jsonObject.put("pId", UUID.randomUUID());
            jsonObject.put("key", UUID.randomUUID());
            jsonObject.put("items", "json对象");
            jsonObject.put("equipment_type", "Windows PC");
            TextMessage message = session.createTextMessage(jsonObject.toJSONString());

            batchCreatemessage(message,1, producer);
            //  5、结束流程
            session.commit();
            session.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 指定生产者生产一定数量的某消息
     * @param message       消息体
     * @param count         批量生产的条目数
     * @param producer      生产者
     * @return
     */
    public static void batchCreatemessage(TextMessage message,  int count, MessageProducer producer) throws JMSException {
        for (int i = 0; i < count; i++) {
            producer.send(message);
        }
        System.out.println("生产者编号：" + producer.hashCode() + "\t生产 " + count + " 条消息。消息内容如下：\n" + message.getText());
    }
}
