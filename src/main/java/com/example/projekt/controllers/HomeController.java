package com.example.projekt.controllers;

import com.example.projekt.dao.ILaureateRepository;
import com.example.projekt.dao.INobelPrizeRepository;
import com.example.projekt.entities.Laureate;
import com.example.projekt.entities.NobelPrize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {


    @GetMapping("/")
    public String displayHome(){
        return "index";
    }

}
