package com.redwood.rottenpotato.services;

import com.redwood.rottenpotato.enums.AjaxCallStatus;
import com.redwood.rottenpotato.models.Movie;
import com.redwood.rottenpotato.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;

import javax.annotation.PostConstruct;
import java.util.Date;

@Service
public class MovieService
{
    @Autowired
    private MovieRepository movieRepository;

    public void addMovie(String movieName, Date movieDate, double rate, double boxOffice)
    {
        movieRepository.save(new Movie(movieName, movieDate, rate, boxOffice));
    }
}
