package cn.springmvc.controller;

import cn.springmvc.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Vincent Wang(王言斌)) on 2017/2/26 0026.
 * Version 1.0.0
 * Description
 */
@Controller
@RequestMapping("/index")
public class IndexController extends BaseController{

    @RequestMapping("/index")
    public  Object index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index2");
        mv.addObject("title", "first page of intellij idea.");
        mv.addObject("content", "Hello, IntelliJ IDEA World!");
        return mv;
    }
}
