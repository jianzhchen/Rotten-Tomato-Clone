package com.redwood.rottenpotato.services;

import com.redwood.rottenpotato.models.Movie;
import com.redwood.rottenpotato.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class TVService {

    @Autowired
    private MovieRepository movieRepository;

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
}
