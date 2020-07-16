package com.sunvirgo.springbootstep05.controller;

import com.sunvirgo.starter.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类说明:
 *
 * @author : 黄刚
 * @date : 2020/6/30 22:27
 **/
@RestController
public class HelloController {
    @Autowired
    HelloService helloService;

    @GetMapping("/hello")
    public String hello(){
        return helloService.sayHelloSunvirgo("zhangsan");
    }
}
