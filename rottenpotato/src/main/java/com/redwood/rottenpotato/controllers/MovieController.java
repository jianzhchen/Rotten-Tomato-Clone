package com.redwood.rottenpotato.controllers;

import com.redwood.rottenpotato.enums.MovieStatus;
import com.redwood.rottenpotato.models.Movie;
import com.redwood.rottenpotato.services.MovieService;
import com.redwood.rottenpotato.services.MovieValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


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

    @RequestMapping(value = "/getTopBoxOffice", method = RequestMethod.GET)
    public List<Movie> getTopBoxOffice() {
        return movieService.getTopBoxOffice();

    }

    @RequestMapping(value = "/loadMovies", method = RequestMethod.GET)
    public List<Movie> loadMovies(){
        List<Movie> movies = movieService.loadMovies();
        Collections.sort(movies, new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                if(o1.getMovieDate().before(o2.getMovieDate())){
                    return 1;
                }else if(o1.getMovieDate().equals(o2.getMovieDate())){
                    return 0;
                }
                return -1;
            }
        });
        return movies;
    }
}