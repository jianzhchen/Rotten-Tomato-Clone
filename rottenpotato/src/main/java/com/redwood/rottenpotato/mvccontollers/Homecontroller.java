package com.redwood.rottenpotato.mvccontollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class Homecontroller {

    @GetMapping(value = {"", "/", "index.html"})
    public String greeting(Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("isLogin", false);
        } else {
            model.addAttribute("isLogin", true);
            model.addAttribute("username", principal.getName());
        }
        return "index.html";
    }
}
