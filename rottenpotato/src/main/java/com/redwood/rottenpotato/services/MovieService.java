package com.redwood.rottenpotato.services;

import com.redwood.rottenpotato.enums.AjaxCallStatus;
import com.redwood.rottenpotato.models.Movie;
import com.redwood.rottenpotato.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;

import javax.annotation.PostConstruct;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public void addMovie(String movieName, String movieDate, double rate, double boxOffice) {
        try {
            DateFormat formatter;
            Date date1;
            formatter = new SimpleDateFormat("yyyy-mm-dd");
            date1 = formatter.parse(movieDate);

            Movie movie = new Movie(movieName, date1, rate, boxOffice);
            movieRepository.save(movie);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
