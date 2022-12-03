package com.example.projekt.controllers;

import com.example.projekt.dao.ILaureateRepository;
import com.example.projekt.dao.INobelPrizeRepository;
import com.example.projekt.entities.Laureate;
import com.example.projekt.entities.NobelPrize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/prizes")
public class NobelPrizeController {

    @Autowired
    INobelPrizeRepository nobelPrizeRepository;

    @Autowired
    ILaureateRepository laureateRepository;

    @GetMapping
    public String displayPrizes(Model model){
        List<NobelPrize> prizes = nobelPrizeRepository.findAll();
        model.addAttribute("prizes", prizes);
        return "prizes/prizes";
    }

    @GetMapping("/newPrize")
    public String displayPrizeForm(Model model){
        NobelPrize prize = new NobelPrize();
        List<Laureate> laureates = laureateRepository.findAll();
        laureates.sort(new Comparator<Laureate>() {
            @Override
            public int compare(Laureate o1, Laureate o2) {
                return o1.getGivenName().compareTo(o2.getGivenName());
            }
        });
        HashSet<String> categories = new HashSet<String>();
        List<NobelPrize> prizes = nobelPrizeRepository.findAll();
        for (NobelPrize prizex : prizes) {
            categories.add(prizex.getCategory());
        }
        model.addAttribute("prize",prize);
        model.addAttribute("laureates",laureates);
        model.addAttribute("categories",categories);
        return "/prizes/new-prize";
    }

    @PostMapping("/save")
    public String createPrize(NobelPrize prize, Model model){
        nobelPrizeRepository.save(prize);
        return "redirect:/prizes";
    }

    @GetMapping("/delete")
    public String deletePrize(@RequestParam Long prizeId){
        nobelPrizeRepository.deleteById(prizeId);
        return "redirect:/prizes";
    }

    @GetMapping("/editForm")
    public String displayEditPrizeForm(@RequestParam Long prizeId, Model model){
        NobelPrize prize = nobelPrizeRepository.findById(prizeId).get();
        List<Laureate> laureates = laureateRepository.findAll();
        laureates.sort(new Comparator<Laureate>() {
            @Override
            public int compare(Laureate o1, Laureate o2) {
                return o1.getGivenName().compareTo(o2.getGivenName());
            }
        });
        HashSet<String> categories = new HashSet<String>();
        List<NobelPrize> prizes = nobelPrizeRepository.findAll();
        for (NobelPrize prizex : prizes) {
            categories.add(prizex.getCategory());
        }
        model.addAttribute("laureates",laureates);
        model.addAttribute("categories",categories);
        model.addAttribute("prize",prize);
        System.out.println(prize.getPrizeId());
        return "/prizes/editPrize";
    }

    @PostMapping("/update")
    public String updatePrize(@ModelAttribute NobelPrize nobelPrize, @RequestParam Long prizeId) {
        nobelPrize.setPrizeId(prizeId);
        nobelPrizeRepository.save(nobelPrize);
        return "redirect:/prizes";
    }

}
