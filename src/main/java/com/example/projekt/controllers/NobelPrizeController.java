package com.example.projekt.controllers;

import com.example.projekt.dao.INobelPrizeRepository;
import com.example.projekt.entities.NobelPrize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/prizes")
public class NobelPrizeController {

    @Autowired
    INobelPrizeRepository nobelPrizeRepository;

    @GetMapping
    public String displayPrizes(Model model){
        List<NobelPrize> prizes = nobelPrizeRepository.findAll();
        model.addAttribute("prizes", prizes);
        return "prizes/prizes";
    }

    @GetMapping("/newPrize")
    public String displayPrizeForm(Model model){
        NobelPrize prize = new NobelPrize();
        model.addAttribute("prize",prize);
        return "/prizes/new-prize";
    }

    @PostMapping("/save")
    public String createPrize(NobelPrize prize, Model model){
        nobelPrizeRepository.save(prize);
        return "redirect:/prizes/newPrize";
    }

}