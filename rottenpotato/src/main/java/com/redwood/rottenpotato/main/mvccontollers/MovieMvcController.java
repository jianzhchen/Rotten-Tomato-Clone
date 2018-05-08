package com.redwood.rottenpotato.main.mvccontollers;

import com.redwood.rottenpotato.main.models.Actor;
import com.redwood.rottenpotato.main.models.CriticReview;
import com.redwood.rottenpotato.main.models.Movie;
import com.redwood.rottenpotato.main.models.UserRating;
import com.redwood.rottenpotato.main.repositories.ActorRepository;
import com.redwood.rottenpotato.main.repositories.CriticRepository;
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

import java.security.Principal;
import java.util.*;

@Controller
public class MovieMvcController {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private CriticReviewRepository criticReviewRepository;
    @Autowired
    private CriticRepository criticRepository;
    @Autowired
    private UserRatingRepository userRatingRepository;


    @GetMapping(value = "m/{movieKey}")
    public String movieDetail(@PathVariable("movieKey") String movieKey, Model model)
    {
        Movie movie = movieRepository.findByMovieKey(movieKey);
        if (movie == null)
        {
            model.addAttribute("exist", false);
        }
        model.addAttribute("movieKey", movie.getMovieKey());
        model.addAttribute("name", movie.getName());
        model.addAttribute("info", movie.getInfo());
        model.addAttribute("movieRating", movie.getRating());
        model.addAttribute("genre", movie.getGenre());
        model.addAttribute("director", movie.getDirector());
        model.addAttribute("writer", movie.getWriter());
        model.addAttribute("inTheater", movie.getInTheaters());
        model.addAttribute("onDisc", movie.getOnDisc());
        model.addAttribute("boxOffice", boxOfficeTransfer(movie.getBoxOffice()));
        model.addAttribute("runTime", movie.getRunTime());
        model.addAttribute("studio", movie.getStudio());
        String cast = movie.getCast();
        List<String> actorKeys = Arrays.asList(cast.split("\\s*,\\s*"));
        List<HashMap> actors = new ArrayList<>();
        for (String actorKey : actorKeys)
        {
            HashMap<String, String> actorMap = new HashMap<>();
            Actor actor = actorRepository.findByActorKey(actorKey);
            actors.add(actorMap);
        }

        model.addAttribute("casts",this.getActorNamesAndNamesByKeys(castsTransfer(movie.getCast())));


        List<CriticReview> crs = criticReviewRepository.findByItemKey(movieKey);
        int criticScore = 0;
        int criticScoreCount = 0;
        for (CriticReview criticReview : crs) {
            if (criticReview.getReviewRating() != 0) {
                criticScore = criticScore + criticReview.getReviewRating();
                criticScoreCount++;
            }
        }
        model.addAttribute("criticScoreCount", criticScoreCount);
        if (criticScoreCount == 0) {
            model.addAttribute("criticRating", "N/A");
        } else {
            model.addAttribute("criticRating", String.format("%.2f", (double)criticScore / criticScoreCount));
        }

        List<UserRating> userRatings = userRatingRepository.findByItemKey(movieKey);
        int userScore = 0;
        int userScoreCount = 0;
        for (UserRating userRating : userRatings) {
            if (userRating.getRating() != 0) {
                userScore = userScore + userRating.getRating();
                userScoreCount++;
            }
        }
        if (userScoreCount == 0) {
            model.addAttribute("userRating", "N/A");
        } else {
            model.addAttribute("userRating", String.format("%.2f", (double)userScore / userScoreCount));
        }

        //CriticReviews
        //1. get criticReview based on movieKey(itemKey)
        List<CriticReview> criticReviews = criticReviewRepository.findByItemKey(movie.getMovieKey());

        //2. For the criticReview, create a hashmap of reviews, and put all hashmaps into a list
        List<HashMap> reviews = new ArrayList<>();
        for (CriticReview cr : criticReviews)
        {
            //one review
            HashMap<String, String> aReview = new HashMap<>();

            if(cr.getReviewRating()==0)
            {
                aReview.put("score", "No Score");
            }
            else
            {
                aReview.put("score", Integer.toString(cr.getReviewRating()));
            }
            aReview.put("date", cr.getReviewTime());
            aReview.put("content", cr.getReviewContent());
            aReview.put("criticKey", cr.getCriticKey());

            String criticName = this.criticRepository.findByCriticKey(cr.getCriticKey()).getCriticName();
            aReview.put("criticName", criticName);
            reviews.add(aReview);
        }
        model.addAttribute("reviews", reviews);


        return "movieInfo.html";

    }

    @GetMapping(value = "m/d/{page}")
    public String movieByDate(@PathVariable("page") int page, Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("isLogin", false);
        } else {
            model.addAttribute("isLogin", true);
            model.addAttribute("username", principal.getName());
        }
        List<Movie> movies = movieRepository.findTop8ByOrderByInTheatersTimeDesc(PageRequest.of(page, 8));
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
        model.addAttribute("page", page);
        return "movieToDate.html";
    }

    @GetMapping(value = "m/b/{page}")
    public String movieByBox(@PathVariable("page") int page, Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("isLogin", false);
        } else {
            model.addAttribute("isLogin", true);
            model.addAttribute("username", principal.getName());
        }
        List<Movie> movies = movieRepository.findTop8ByOrderByBoxOfficeDesc(PageRequest.of(page, 8));
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
        model.addAttribute("page", page);

        return "movieToBox.html";
    }


    public String boxOfficeTransfer(long boxOffice) {
        String boxOfficeStr;

        if (boxOffice >= 1000000000) {
            boxOfficeStr = "$" + boxOffice / 1000000000 + "." + (boxOffice % 1000000000) / 100000000 + "B";
        } else if (boxOffice >= 1000000) {
            boxOfficeStr = "$" + boxOffice / 1000000 + "." + (boxOffice % 1000000) / 100000 + "M";
        } else if (boxOffice > 1000) {
            boxOfficeStr = "$" + boxOffice / 1000 + "." + (boxOffice % 1000) / 100 + "K";
        } else {
            boxOfficeStr = "$" + boxOffice;
        }
        return boxOfficeStr;
    }


    public String[] castsTransfer(String casts){
        String castsArray[] = casts.split(",");
        return castsArray;
    }

    public List<Map>castNamesAndKeys(String[] castsArr){
        List<Map> tempList = new ArrayList<>();
        for(int i =  0; i <= castsArr.length - 1 && i < 5; i++){

            Map<String, String> map = new HashMap<>();
            map.put("actorKey", castsArr[i]);
            map.put("actorName",toCastName(castsArr[i]));
            tempList.add(map);
        }
        return tempList;
    }

    //6 actors in maximum
    public List<Map>getActorNamesAndNamesByKeys(String[] castsArr)
    {
        List<Map> tempList = new ArrayList<>();
        for(int i =  0; i <= castsArr.length - 1 && i < 6; i++)
        {
            Map<String, String> map = new HashMap<>();
            map.put("actorKey", castsArr[i]);
            map.put("actorName", this.actorRepository.findByActorKey(castsArr[i]).getActorName());
            tempList.add(map);
        }
        return tempList;
    }

    public String toCastName(String cast){
        String temp = cast.replace("_", " ");
        temp = temp.replace("-", " ");
        return temp;
    }
}
