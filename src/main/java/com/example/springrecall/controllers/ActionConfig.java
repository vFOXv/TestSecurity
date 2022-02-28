package com.example.springrecall.controllers;

import com.example.springrecall.Servis.MyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/action")
public class ActionConfig {

    private final MyService myService;

    public ActionConfig(MyService myService) {
        this.myService = myService;
    }

    @PostMapping("/delete/{id}")
    public String deleteAnimal(@PathVariable("id") Long id){
        myService.deleteAnimal(id);
        return "redirect:/show";
    }
}
