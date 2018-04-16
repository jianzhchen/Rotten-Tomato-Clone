package com.redwood.rottenpotato.controllers;

import com.redwood.rottenpotato.enums.MovieStatus;
import com.redwood.rottenpotato.services.MovieService;
import com.redwood.rottenpotato.services.MovieValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/movie")
public class MovieController
{
    @Autowired
    //Used for validate the new movie
    private MovieValidationService movieValidationService;

    @Autowired
    //Used for validate add movie if it passes movieValidation
    private MovieService movieService;


    @RequestMapping(value = "/addMovie", method = RequestMethod.POST)
    public String addMovie(@RequestParam(value = "name") String name,
                               @RequestParam(value = "date") String date,
                               @RequestParam(value = "rate") double rate,
                               @RequestParam(value = "boxOffice") double boxOffice)
    {
        //Check if the new movie is valid by using movieValiadationService, if so, then return error message by service
        if (!movieValidationService.validMovie(name, date, rate, boxOffice))
        {
            return MovieStatus.FAILURE.toString();
        }

        //Try to add movie to database by using movieService, then return a string of jsonObject which contains status
        movieService.addMovie(name, date, rate, boxOffice);
        return MovieStatus.SUCCESS.toString();
    }
}