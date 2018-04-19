package com.redwood.rottenpotato.mvccontollers;

import com.redwood.rottenpotato.models.NewMovie;
import com.redwood.rottenpotato.models.Temp;
import com.redwood.rottenpotato.repositories.NewMovieRepository;
import com.redwood.rottenpotato.services.NewMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MovieMvcController {

    @Autowired
    private NewMovieRepository newMovieRepository;
    @Autowired
    private NewMovieService newMovieService;

    @GetMapping(value = "searchResult.html")
    public String search(@RequestParam("term") String searchTerm, Model model) {
        List<NewMovie> resultList = newMovieRepository.searchByName(searchTerm);
        model.addAttribute("name", resultList.get(0).getName());
        model.addAttribute("info", resultList.get(0).getInfo());
        return "searchResult.html";
    }

    @GetMapping(value = "topBox.html")
    public String topbox(@RequestParam("p") int p, Model model) {
        newMovieService.top10BoxWithPage(model, p);
        return "topBox.html";
    }

}
