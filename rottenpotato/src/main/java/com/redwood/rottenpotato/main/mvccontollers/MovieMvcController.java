package com.redwood.rottenpotato.main.mvccontollers;

import com.redwood.rottenpotato.main.models.Actor;
import com.redwood.rottenpotato.main.models.CriticReview;
import com.redwood.rottenpotato.main.models.Movie;
import com.redwood.rottenpotato.main.models.UserRating;
import com.redwood.rottenpotato.main.repositories.ActorRepository;
import com.redwood.rottenpotato.main.repositories.CriticReviewRepository;
import com.redwood.rottenpotato.main.repositories.MovieRepository;
import com.redwood.rottenpotato.main.repositories.UserRatingRepository;
import com.redwood.rottenpotato.main.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Controller
public class MovieMvcController {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private CriticReviewRepository criticReviewRepository;
    @Autowired
    private UserRatingRepository userRatingRepository;


    @GetMapping(value = "m/{movieKey}")
    public String movieDetail(@PathVariable("movieKey") String movieKey, Model model) {
        Movie movie = movieRepository.findByMovieKey(movieKey);
        if(movie==null){
            model.addAttribute("exist", false);
        }
        model.addAttribute("name", movie.getName());
        model.addAttribute("info", movie.getInfo());
        model.addAttribute("movieRating", movie.getRating());
        model.addAttribute("genre", movie.getGenre());
        model.addAttribute("director", movie.getDirector());
        model.addAttribute("writer", movie.getWriter());
        model.addAttribute("inTheater", movie.getInTheaters());
        model.addAttribute("onDisc", movie.getOnDisc());
        long boxOffice = movie.getBoxOffice();
        String boxOfficeText = "$";
        if (boxOffice == 0) {
            boxOfficeText = "";
        } else {
            boxOfficeText = boxOfficeText + boxOffice;
        }
        model.addAttribute("boxOffice", boxOfficeText);
        model.addAttribute("runTime", movie.getRunTime());
        model.addAttribute("studio", movie.getStudio());
        String cast = movie.getCast();
        List<String> actorKeys = Arrays.asList(cast.split("\\s*,\\s*"));
        List<HashMap> actors = new ArrayList<>();
        for (String actorKey : actorKeys) {
            HashMap<String, String> actorMap = new HashMap<>();
            Actor actor = actorRepository.findByActorKey(actorKey);
//            actorMap.put("name", actor.getActorName());
//            actorMap.put("key", actor.getActorName());
            actors.add(actorMap);
        }
        model.addAttribute("actors", actors);
        List<CriticReview> crs = criticReviewRepository.findByItemKey(movieKey);
        int criticScore = 0;
        int criticScoreCount = 0;
        for (CriticReview criticReview : crs) {
            if (criticReview.getReviewRating() != 0) {
                criticScore = criticScore + criticReview.getReviewRating();
                criticScoreCount++;
            }
        }
//        model.addAttribute("criticRating", String.format("%.2f", criticScore / criticScoreCount));

        List<UserRating> userRatings = userRatingRepository.findByItemKey(movieKey);
        int userScore = 0;
        int userScoreCount = 0;
        for (UserRating userRating : userRatings) {
            if (userRating.getRating() != 0) {
                userScore = userScore + userRating.getRating();
                userScoreCount++;
            }
        }
//        model.addAttribute("userRating", String.format("%.2f", userScore / userScoreCount));
        //TODO poster
        return "movieInfo.html";
    }

    @GetMapping(value = "m/d/{page}")
    public String movieByDate(@PathVariable("page") int page, Model model) {
        List<Movie> movies = movieRepository.findTop10ByOrderByInTheatersTimeDesc(PageRequest.of(page, 8));
        List<HashMap> movieList = new ArrayList<>();
        for (Movie movie : movies) {
            HashMap<String, String> movieDetail = new HashMap<>();
            movieDetail.put("name", movie.getName());
            movieDetail.put("key", movie.getMovieKey());
            movieDetail.put("date", movie.getInTheaters());
            //TODO
            movieList.add(movieDetail);
        }
        model.addAttribute("movies", movieList);
        model.addAttribute("page",page);
        return "movieToDate.html";
    }

    @GetMapping(value = "m/b/{page}")
    public String movieByBox(@PathVariable("page") int page, Model model) {
        List<Movie> movies = movieRepository.findTop10ByOrderByBoxOfficeDesc(PageRequest.of(page, 8));
        List<HashMap> movieList = new ArrayList<>();
        for (Movie movie : movies) {
            HashMap<String, String> movieDetail = new HashMap<>();
            movieDetail.put("name", movie.getName());
            movieDetail.put("key", movie.getMovieKey());
            movieDetail.put("boxOffice", "$" + movie.getBoxOffice());
            //TODO
            movieList.add(movieDetail);
        }
        model.addAttribute("movies", movieList);
        model.addAttribute("page",page);

        return "movieToBox.html";
    }
}
