package com.redwood.rottenpotato.services;

import com.redwood.rottenpotato.enums.AjaxCallStatus;
import com.redwood.rottenpotato.exception.MovieException;
import com.redwood.rottenpotato.models.Movie;
import com.redwood.rottenpotato.repositories.MovieRepository;
import com.redwood.rottenpotato.security.exception.EmailExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;

import javax.annotation.PostConstruct;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MovieService
{
    @Autowired
    private MovieRepository movieRepository;

    public void addMovie(String movieName, String movieDate, double rate, double boxOffice)
    {
        //add the movie to the movie repository persistent layer
        try
        {
            DateFormat formatter;
            Date date1;
            formatter = new SimpleDateFormat("yyyy-mm-dd");
            date1 = formatter.parse(movieDate);

            Movie movie = new Movie(movieName, date1, rate, boxOffice);
            movieRepository.save(movie);
        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }

    }

    public List<Movie> getTopBoxOffice(){
        List<Movie> list = new ArrayList<Movie>();
        Iterable<Movie> movies = movieRepository.findAll();
        movies.forEach(e->{
            list.add(e);
        });
        Collections.sort(list, new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                if (o1.getBoxOffice() < o2.getBoxOffice()){
                    return 1;
                }else if(o1.getBoxOffice() == o2.getBoxOffice()){
                    return 0;
                }else{
                    return -1;
                }
            }
        });
        List<Movie> topTen = new ArrayList<Movie>();
        for(int i=0;i<10;i++){
            topTen.add(list.get(i));
        }
        return topTen;
    }
}
