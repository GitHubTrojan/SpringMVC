package cn.springmvc.controller;

import cn.springmvc.base.BaseController;
import cn.springmvc.service.LoginService;
import cn.springmvc.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by Vincent Wang(王言斌)) on 2017/2/26 0026.
 * Version 1.0.0
 * Description
 */
@Controller
@RequestMapping("/index")
public class IndexController extends BaseController{

    @Resource(name = "loginService")
    private LoginService loginService;

    @RequestMapping("/index")
    @ResponseBody
    public Object index(@RequestParam("uid") int uid) {
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("index2");
//        mv.addObject("title", "first page of intellij idea.");
        Map<String, Object> data = new HashMap<>();
        User user = loginService.selectByPrimaryKey(uid);
        data.put("user", user == null? "I'm sorry, no user has been found." : user);
//        mv.addObject("content", user);
//        System.out.println(user);
        return data;
    }
    @RequestMapping("/index2")
    @ResponseBody
    public String index2(@RequestParam("uid") int uid) {
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("index2");
//        mv.addObject("title", "first page of intellij idea.");
//        Map<String, Object> data = new HashMap<String, Object>();
        User user = loginService.selectByPrimaryKey(uid);
//        data.put("user", user == null? "I'm sorry, no user has been found." : user);
//        mv.addObject("content", user);
        System.out.println("查询到的用户名：\t" + user.getUname());
        user.setUname("Vincent Wang Only");
//        try {
//            int result = 0;
//            result = loginService.updateByPrimaryKey(user);
//        } catch (IllegalArgumentException e) {
//
//        } finally {
//            User user1 = loginService.selectByPrimaryKey(uid);
//            System.out.println("查询到的用户名：\t" + user1.getUname());
//        }

            int result = 0;
            result = loginService.updateByPrimaryKey(user);

            User user1 = loginService.selectByPrimaryKey(uid);
            System.out.println("查询到的用户名：\t" + user1.getUname());

        return "测试";
    }
}
