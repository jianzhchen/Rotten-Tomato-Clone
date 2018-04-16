package com.redwood.rottenpotato.controllers;


import com.redwood.rottenpotato.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/movie")
public class MovieController
{
    @Autowired
    private MovieService movieService;

    @RequestMapping(value = "/addMovie", method = RequestMethod.POST)
    public String addMovie(@RequestParam(value = "name") String name,
                           @RequestParam(value = "date") String date,
                           @RequestParam(value = "rate") double rate,
                           @RequestParam(value = "boxOffice") double boxOffice)
    {
        try
        {
            DateFormat formatter;
            Date date1;
            formatter = new SimpleDateFormat("yyyy-mm-dd");
            date1 = formatter.parse(date);

            movieService.addMovie(name, date1, rate, boxOffice );
        }
        catch (ParseException e)
        {

        }

        //For debugging purpose
        System.out.print(date);
        return date;
    }
}