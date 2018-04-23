package com.redwood.rottenpotato.main.mvccontollers;

import com.redwood.rottenpotato.main.models.Actor;
import com.redwood.rottenpotato.main.models.Movie;
import com.redwood.rottenpotato.main.repositories.ActorRepository;
import com.redwood.rottenpotato.main.repositories.MovieRepository;
import com.redwood.rottenpotato.main.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Controller
public class MovieMvcController {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private MovieService movieService;

    @GetMapping(value = "topBox.html")
    public String topbox(@RequestParam("p") int p, Model model) {
        movieService.top10BoxWithPage(model, p);
        return "topBox.html";
    }

    @GetMapping(value = "m/{movieKey}")
    public String movieDetail(@PathVariable("movieKey") String movieKey, Model model) {
        Movie movie = movieRepository.findByMovieKey(movieKey);
        model.addAttribute("name", movie.getName());
        model.addAttribute("info", movie.getInfo());
        model.addAttribute("movieRating", movie.getRating());
        model.addAttribute("genre", movie.getGenre());
        model.addAttribute("director", movie.getDirector());
        model.addAttribute("writer", movie.getWriter());
        model.addAttribute("inTheater", movie.getInTheaters());
        model.addAttribute("onDisc", movie.getOnDisc());
        long boxOffice = movie.getBoxOffice();
        String boxOfficeText = "$";
        if (boxOffice == 0) {
            boxOfficeText = "";
        } else {
            boxOfficeText = boxOfficeText + boxOffice;
        }
        model.addAttribute("boxOffice", boxOfficeText);
        model.addAttribute("runTime", movie.getRunTime());
        model.addAttribute("studio", movie.getStudio());
        String cast = movie.getCast();
        List<String> actorKeys = Arrays.asList(cast.split("\\s*,\\s*"));
        HashMap<String, String> actorsMap = new HashMap<>();
        for(String actorKey :actorKeys){
            Actor actor = actorRepository.findByActorKey(actorKey);
        }

        return "topBox.html";

    }

}
