package com.redwood.rottenpotato.main.restcontrollers;

import com.redwood.rottenpotato.main.models.Movie;
import com.redwood.rottenpotato.main.repositories.MovieRepository;
import com.redwood.rottenpotato.main.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieRestController {

    @Autowired
    private MovieRepository MovieRepository;
    @Autowired
    private MovieService movieService;


}
