package com.example.springrecall.controllers;

import com.example.springrecall.Services.MyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.sql.SQLException;

@Controller
//@RequestMapping("/show")
public class ShowController {
//    private final MyService myService;
//
//    public ShowController(MyService myService) {
//        this.myService = myService;
//    }
//
//    //создание списка всех животных и передача его на HTML
//    @GetMapping("")
//    //Principal это информация о текущем пользователе
//    private String showAll(Model model, Principal principal) {
//        model.addAttribute("AllAnimals", myService.showAll());
//        model.addAttribute("ThisAnimal", "Get This Animal :) "+principal.getName());
//        model.addAttribute("Delete","Delete");
//        return "Show/show_all";
//    }
//
//    //показвает конкретную запись в дневнике для просмотра и редакции
//    @GetMapping("/this_animal/{id}")
//    private String showThisAnimal(@PathVariable("id") Long id, Model model, Principal principal) throws SQLException, ClassNotFoundException {
//        model.addAttribute("ThisAnimal", myService.showThisAnimal(id));
//        System.out.println("------------------------} "+myService.showThisAnimal(id)+" "+principal.getName());
//        model.addAttribute("NameUser", principal.getName());
//        return "Show/this_animal";
//    }
//
//    @GetMapping("/read_profile")
//    public String pageForReadProfile(Principal principal, Model model){
//        model.addAttribute("ReadProfile", "This page read "+ principal.getName());
//        return "Show/read_profile";
//    }
//
//    @GetMapping("/only_for_admins")
//    public String pageOnlyForAdmins(Principal principal, Model model){
//        model.addAttribute("OnlyForAdmins","This page only for admins "+principal.getName());
//        return "Show/only_for_admins";
//    }
}
