package com.example.projekt.web;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class HelloController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String sayHello() {
        return "<h2>hello world </h2>";
    }

    @RequestMapping("/list")
    public List<String> jsonList(){
        return Arrays.asList("Test1","Test2");
    }

}
