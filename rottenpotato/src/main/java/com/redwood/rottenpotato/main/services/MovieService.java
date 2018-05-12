package com.redwood.rottenpotato.main.services;

import com.redwood.rottenpotato.main.models.Movie;
import com.redwood.rottenpotato.main.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import sun.util.resources.cldr.aa.CalendarData_aa_DJ;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MovieService {
    @Autowired
    private MovieRepository MovieRepository;


    public List<Map> top10BoxWithPage(Model model, int page) {
        List<Map> templist = new ArrayList<>();
        for (Movie temp : MovieRepository.findTop10ByOrderByBoxOfficeDesc(PageRequest.of(page, 10))) {
            Map<String, String> map = new HashMap<>();

            map.put("movieName", temp.getName());
            map.put("key",temp.getMovieKey());
            map.put("boxOffice", boxOfficeTransfer(temp.getBoxOffice()));
            templist.add(map);
        }
        return templist;
    }

    public List<Map> top10InTheatersDatePage(Model model, int page) {
        List<Map> templist = new ArrayList<>();
        for (Movie temp : MovieRepository.findTop10ByOrderByInTheatersTimeDesc(PageRequest.of(page, 10))) {
            Map<String, String> map = new HashMap<>();

            map.put("movieName", temp.getName());
            map.put("key",temp.getMovieKey());
            map.put("date",temp.getInTheaters());
            DateFormat formatter = new SimpleDateFormat("MMM-dd");
            templist.add(map);
        }
        return templist;
    }


    public String boxOfficeTransfer(long boxOffice){
        String boxOfficeStr;

        if(boxOffice >= 1000000000){
            boxOfficeStr = "$" + boxOffice / 1000000000 + "." + (boxOffice % 1000000000) / 100000000 + "B";
        }else if (boxOffice >= 1000000){
            boxOfficeStr = "$" + boxOffice / 1000000 + "." + (boxOffice % 1000000) / 100000 + "M";
        }else if (boxOffice > 1000){
            boxOfficeStr = "$" + boxOffice / 1000 + "." + (boxOffice % 1000) / 100 + "K";
        }else{
            boxOfficeStr = "$" + boxOffice;
        }
        return boxOfficeStr;
    }

    public List<Map> openingThisWeek(Model model, int page){
        List<Map> templist = new ArrayList<>();

        Date date = new Date();
        Date firstDate1 = new Date(118, 4, 15);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
        cal.clear(Calendar.MINUTE);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MILLISECOND);

        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        Date dateFirst = cal.getTime();

        for (int i = 0; i < 7; i++) {

            cal.add(Calendar.DATE, 1);
        }

        Date dateLast = cal.getTime();

        for (Movie movie : MovieRepository.findTop100ByOrderByInTheatersTimeDesc(PageRequest.of(page, 1))) {
            if(movie.getInTheatersTime() != null){
//                if(movie.getInTheatersTime().getYear() == date.getYear()  & movie.getInTheatersTime().getMonth() == date.getMonth() & movie.getInTheatersTime().getDate() / 7 == date.getDate() / 7){
                if(movie.getInTheatersTime().after(dateFirst) & movie.getInTheatersTime().before(dateLast)){
                    Map<String, String> map = new HashMap<>();
                    map.put("movieName", movie.getName());
                    map.put("key",movie.getMovieKey());
                    map.put("date",movie.getInTheaters());
                    templist.add(map);
                }
            }
        }

        return templist;
    }


}
