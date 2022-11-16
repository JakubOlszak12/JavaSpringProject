package com.example.projekt.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

     @RequestMapping("/home")
    public String home(){
            return "index";
     }

}
