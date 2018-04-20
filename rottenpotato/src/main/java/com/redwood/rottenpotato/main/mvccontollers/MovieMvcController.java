package com.redwood.rottenpotato.main.mvccontollers;

import com.redwood.rottenpotato.main.models.Movie;
import com.redwood.rottenpotato.main.repositories.MovieRepository;
import com.redwood.rottenpotato.main.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MovieMvcController {

    @Autowired
    private MovieRepository MovieRepository;
    @Autowired
    private MovieService movieService;

    @GetMapping(value = "searchResult.html")
    public String search(@RequestParam("term") String searchTerm, Model model) {
        List<Movie> resultList = MovieRepository.searchByName(searchTerm);
        model.addAttribute("name", resultList.get(0).getName());
        model.addAttribute("info", resultList.get(0).getInfo());
        return "searchResult.html";
    }

    @GetMapping(value = "topBox.html")
    public String topbox(@RequestParam("p") int p, Model model) {
        movieService.top10BoxWithPage(model, p);
        return "topBox.html";
    }

}
