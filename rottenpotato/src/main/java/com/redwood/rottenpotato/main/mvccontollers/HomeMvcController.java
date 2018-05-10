package com.redwood.rottenpotato.main.mvccontollers;

import com.redwood.rottenpotato.main.services.MovieService;
import com.redwood.rottenpotato.main.services.TVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeMvcController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private TVService TVService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping(value = {"", "/", "index.html"})
    public String greeting(Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("isLogin", false);
        } else {
            model.addAttribute("isLogin", true);
            model.addAttribute("username", principal.getName());
        }

        model.addAttribute("topBoxOffice", movieService.top10BoxWithPage(model, 0));
        model.addAttribute("movieOpeningThisWeek", movieService.top10InTheatersDatePage(model, 0));
        model.addAttribute("newTVShows", TVService.top10TVDatePage(model, 0));

        return "index.html";
    }

    @GetMapping("/about")
    public String about(){
        return "about.html";
    }

    @GetMapping("/loginPage")
    public String loginPage(){
        return "login.html";
    }

    @GetMapping("/service")
    public String servicePage(){
        return "termOfUse.html";
    }



}
