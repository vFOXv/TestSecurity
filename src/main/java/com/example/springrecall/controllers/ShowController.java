package com.example.springrecall.controllers;

import com.example.springrecall.Servis.MyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;

@Controller
@RequestMapping("/show")
public class ShowController {
    private final MyService myService;

    public ShowController(MyService myService) {
        this.myService = myService;
    }

    //создание списка всех животных и передача его на HTML
    @GetMapping("")
    private String showAll(Model model) {
        model.addAttribute("AllAnimals", myService.showAll());
        model.addAttribute("ThisAnimal", "Get This Animal :)");
        model.addAttribute("Delete","Delete");
        return "Show/show_all";
    }

    //показвает конкретную запись в дневнике для просмотра и редакции
    @GetMapping("/this_animal/{id}")
    private String showThisAnimal(@PathVariable("id") Long id, Model model) throws SQLException, ClassNotFoundException {
        model.addAttribute("ThisAnimal", myService.showThisAnimal(id));
        System.out.println("------------------------} "+myService.showThisAnimal(id));
        return "Show/this_animal";
    }
}
