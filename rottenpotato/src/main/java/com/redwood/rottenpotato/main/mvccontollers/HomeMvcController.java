package com.redwood.rottenpotato.main.mvccontollers;

import com.redwood.rottenpotato.main.services.NewMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class HomeMvcController {

    @Autowired
    private NewMovieService newMovieService;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping(value = {"", "/", "index.html"})
    public String greeting(Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("isLogin", false);
        } else {
            model.addAttribute("isLogin", true);
            model.addAttribute("username", principal.getName());
        }

        model.addAttribute("topBoxOffice", newMovieService.top10BoxWithPage(model, 0));
        model.addAttribute("movieOpeningThisWeek", newMovieService.top10InTheatersDatePage(model, 0));

        return "index.html";
    }
}