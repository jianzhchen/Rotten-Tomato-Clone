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
            Date date1;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            date1 = formatter.parse(movieDate);
            Movie movie = new Movie(movieName, date1, rate, boxOffice);
            movieRepository.save(movie);
        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }

    }

    public List<Movie> getTopTenBoxOffice(){
        List<Movie> list = getTopBoxOffice();
        List<Movie> topTen = new ArrayList<Movie>();
        for(int i=0; i<10; i++){
            topTen.add(list.get(i));
        }
        return topTen;
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
        return list;
    }

    public List<Movie> getMoviesOpeningThisWeek() {
        List<Movie> list = new ArrayList<Movie>();
        Iterable<Movie> movies = movieRepository.findAll();
        movies.forEach(e->{
            list.add(e);
        });
        Date now = new Date();
        Date startDay = getNowWeekSunday(now);
        Date endDay = getNowWeekSaturday(now);
        fliterMovieBetweenTwoDay(list,startDay,endDay);
        return list;


    }

    public  Date getNowWeekSunday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return cal.getTime();
    }

    public  Date getNowWeekSaturday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        return cal.getTime();
    }

    public void fliterMovieBetweenTwoDay(List<Movie> list, Date startDay, Date endDay){
        int index = 0;
        while(index < list.size()){
            if(list.get(index).getMovieDate().before(startDay) ||
                    list.get(index).getMovieDate().after(endDay)){
                list.remove(index);
            }else {
                index++;
            }
        }

    }
}
