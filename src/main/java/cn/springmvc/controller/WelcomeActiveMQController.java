package cn.springmvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Vincent Wang(王言斌)) on 2017/3/4 0004.
 * Version 1.0.0
 * Description  to hte welcome-activemq page
 */
@Controller
public class WelcomeActiveMQController {

    private static final Logger logger = LoggerFactory.getLogger(WelcomeActiveMQController.class);

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public ModelAndView welcome(){
        logger.info("-------------Welcome to ActivcMQ and Spring JMS.-----------------");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("welcome-activemq");
        return mv;
    }

    @RequestMapping(value = "/welcome_", method = RequestMethod.GET)
    public ModelAndView welcome_withTopic(){
        logger.info("-------------Welcome to ActivcMQ and Spring JMS.-----------------");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("welcome-activemq-topic");
        return mv;
    }
}
