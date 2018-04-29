package com.redwood.rottenpotato.main.mvccontollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TVMvcController {

    @GetMapping(value = "t/{TVKey}")
    public String tvDetail(@PathVariable("TVKey") String tVKey, Model model) {
        //TODO
        return "tvInfo.html";
    }
}
