package com.redwood.rottenpotato.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MovieController
{

    @GetMapping("/movies2")
    public String greeting(Model model)
    {
        model.addAttribute("name", "haha");
        return "greeting";
    }
}
