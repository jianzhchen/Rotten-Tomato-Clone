package com.redwood.rottenpotato.main.services;

import com.redwood.rottenpotato.main.models.Movie;
import com.redwood.rottenpotato.main.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class NewMovieService {
    @Autowired
    private MovieRepository MovieRepository;


    public List<Map> top10BoxWithPage(Model model, int page) {
        List<Map> templist = new ArrayList<>();
        for (Movie temp : MovieRepository.findTop10ByOrderByBoxOfficeDesc(PageRequest.of(page, 10))) {
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
        for (Movie temp : MovieRepository.findTop10ByOrderByInTheatersDateDesc(PageRequest.of(page, 10))) {
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
