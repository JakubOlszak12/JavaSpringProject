package com.example.projekt.controllers;

import com.example.projekt.dao.ILaureateRepository;
import com.example.projekt.entities.Laureate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/laureates")
public class LaureateController {

    @Autowired
    ILaureateRepository laureateRepository;

    @GetMapping
    public String displayLaureates(Model model){
        List<Laureate> laureates = laureateRepository.findAll();
        model.addAttribute("laureates",laureates);
        return "laureates/laureates";
    }

    @GetMapping("/newLaureate")
    public String displayLaureateForm(Model model){
        Laureate laureate = new Laureate();
        model.addAttribute("laureate", laureate);
        return "laureates/new-laureate";
    }
    @PostMapping("/save")
    public String createLaureate(Laureate laureate, Model model){
        laureateRepository.save(laureate);
        return  "redirect:/laureates/newLaureate";
    }

}
