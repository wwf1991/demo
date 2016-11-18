package com.cn.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class TestController {

    private final static Logger log = LoggerFactory.getLogger(TestController.class);
    
    @RequestMapping("/viewAll")
    public ModelAndView testEncoding(String name, String pwd){
        
        ModelAndView mv = new ModelAndView();
        log.debug("进入控制器的testEncoding()方法....");
        log.debug("name:{}",name);
        System.out.println("name:" +name);
        log.debug("pwd:{}",pwd);
        log.debug("进入控制器的testEncoding()方法....");
        //mv.setViewName("/hello.jsp");
        mv.setViewName("/hello");
        
        return mv;
    }
    
    @RequestMapping("/query")
    public String query(String name, String pwd, Model model){
        
        //ModelAndView mv = new ModelAndView();
        log.debug("进入控制器的query()方法....");
        log.debug("name:{}",name);
        System.out.println("name:" +name);
        log.debug("pwd:{}",pwd);
        log.debug("进入控制器的query()方法....");
        //mv.setViewName("/hello.jsp");
        //mv.setViewName("/hello");
        model.addAttribute("arg", "这是controller传来的参数");
        
        return "hello";
    }
}
