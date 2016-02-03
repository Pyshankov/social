package com.pyshankov.social.web;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by pyshankov on 31.01.2016.
 */
@Controller
@RequestMapping("/")
public class TestController {

    @Autowired
    ApplicationContext applicationContext;

    @RequestMapping(method = RequestMethod.GET)
    public String hello(Model model){
        model.addAttribute("user",applicationContext.getBean("user"));

        return "hello";
    }
}
