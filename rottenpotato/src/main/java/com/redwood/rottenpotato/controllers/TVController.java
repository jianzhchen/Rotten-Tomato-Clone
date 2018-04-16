package com.redwood.rottenpotato.controllers;

import com.redwood.rottenpotato.models.Movie;
import com.redwood.rottenpotato.services.MovieService;
import com.redwood.rottenpotato.services.TVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/TV")
public class TVController
{
    @Autowired
    private MovieService movieService;

    @Autowired
    private TVService tvService;

    @RequestMapping(value = "/getTopBoxOffice", method = RequestMethod.GET)
    public List<Movie> getTopBoxOffice() {
        return tvService.getTopBoxOffice();

    }


}