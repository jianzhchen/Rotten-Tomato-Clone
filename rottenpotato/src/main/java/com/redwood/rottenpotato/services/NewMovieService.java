package com.redwood.rottenpotato.services;

import com.redwood.rottenpotato.models.Movie;
import com.redwood.rottenpotato.models.NewMovie;
import com.redwood.rottenpotato.repositories.MovieRepository;
import com.redwood.rottenpotato.repositories.NewMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class NewMovieService {
    @Autowired
    private NewMovieRepository newMovieRepository;


    public List<Map> top10BoxWithPage(Model model, int page) {
        List<Map> templist = new ArrayList<>();
        for (NewMovie temp : newMovieRepository.findTop10ByOrderByBoxOfficeDesc(PageRequest.of(page, 10))) {
            Map<String, String> map = new HashMap<>();
            if (temp.getScore() > 0) {
                map.put("rate", String.valueOf(temp.getScore() + "%"));
            } else {
                map.put("rate", "N/A");
            }
            map.put("movieName", temp.getName());
            Long box = temp.getBoxOffice();
            DecimalFormat myFormatter = new DecimalFormat("$###,###.###");
            String output = myFormatter.format(box);
            map.put("boxOffice", output);
            templist.add(map);
        }
        return templist;
    }

    public List<Map> top10InTheatersDatePage(Model model, int page) {
        List<Map> templist = new ArrayList<>();
        for (NewMovie temp : newMovieRepository.findTop10ByOrderByInTheatersDateDesc(PageRequest.of(page, 10))) {
            Map<String, String> map = new HashMap<>();
            if (temp.getScore() > 0) {
                map.put("rate", String.valueOf(temp.getScore() + "%"));
            } else {
                map.put("rate", "N/A");
            }
            map.put("movieName", temp.getName());
            DateFormat formatter = new SimpleDateFormat("MMM-dd");
            map.put("movieDate", formatter.format(temp.getInTheatersDate()));
            templist.add(map);
        }
        return templist;

    }
}
