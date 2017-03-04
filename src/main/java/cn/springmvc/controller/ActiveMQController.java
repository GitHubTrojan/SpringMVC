package cn.springmvc.controller;

import cn.springmvc.demo.activemq.consumer.ConsumerService;
import cn.springmvc.demo.activemq.producer.ProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Vincent Wang(王言斌)) on 2017/3/4 0004.
 * Version 1.0.0
 * Description the message producer  and consumer Controller
 */
@Controller
public class ActiveMQController {

    private static final Logger logger = LoggerFactory.getLogger(ActiveMQController.class);
    //队列名
    @Resource(name="queueDestination")
    private Destination queueDestination;

    //队列消息生产者
    @Resource(name="producerService")
    private ProducerService producer;

    //队列消息消费者
    @Resource(name="consumerService")
    private ConsumerService consumer;

    @RequestMapping(value="/producer", method = RequestMethod.GET)
    public ModelAndView producer(){
        logger.info("=============go producer=================");

        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format( now );
        logger.info(time);

        ModelAndView mv = new ModelAndView();
        mv.addObject("time", time);
        mv.setViewName("jms_producer");
        return mv;
    }

    @RequestMapping(value="/onsend",method=RequestMethod.POST)
    public ModelAndView producer(@RequestParam("message") String message) {
        logger.info("============ send to jms ===========");
        ModelAndView mv = new ModelAndView();
        producer.sendMessage(queueDestination, message);
        mv.setViewName("welcome-activemq");
        return mv;
    }

    @RequestMapping(value="/receive",method = RequestMethod.GET)
    public ModelAndView queue_receive() throws JMSException {
        logger.info("========= receive message ============");
        ModelAndView mv = new ModelAndView();

        TextMessage tm = consumer.receive(queueDestination);
        mv.addObject("textMessage", tm.getText());

        mv.setViewName("queue_receive");
        return mv;
    }

    /*
     * ActiveMQ Manager Test
     */
    @RequestMapping(value="/jms",method=RequestMethod.GET)
    public ModelAndView jmsManager() throws IOException {
        logger.info("========= jms manager ==========");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("welcome");

        JMXServiceURL url = new JMXServiceURL("");
        JMXConnector connector = JMXConnectorFactory.connect(url);
        connector.connect();
        MBeanServerConnection connection = connector.getMBeanServerConnection();

        return mv;
    }
}
