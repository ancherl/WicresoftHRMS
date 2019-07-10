package com.wicresoft.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/request")
public class HelloControl {
    public static final String SUCCESS="success";

    @RequestMapping("/sayHello")
    public String hello(){

        System.out.println("Execute Successfully");
        return SUCCESS;
    }
}
