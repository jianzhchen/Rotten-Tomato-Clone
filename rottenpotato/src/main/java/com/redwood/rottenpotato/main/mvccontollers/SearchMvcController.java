package com.redwood.rottenpotato.main.mvccontollers;

import com.redwood.rottenpotato.main.models.Movie;
import com.redwood.rottenpotato.main.repositories.MovieRepository;
import com.redwood.rottenpotato.main.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class SearchMvcController {

    @Autowired
    private MovieRepository MovieRepository;

    @GetMapping(value = "searchResult")
    public String search(@RequestParam("term") String searchTerm, Model model) {
        List<Movie> resultList = MovieRepository.searchByName(searchTerm);
        List<HashMap> movies = new ArrayList<>();
        for (Movie movie : resultList) {
            HashMap<String, String> movieDetail = new HashMap<>();
            movieDetail.put("name",movie.getName());
            movieDetail.put("info",movie.getInfo());
            movieDetail.put("movieKey",movie.getMovieKey());
            //poster
            movies.add(movieDetail);
        }
        return "searchResult";
    }
}
