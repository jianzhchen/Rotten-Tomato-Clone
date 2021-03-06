package com.redwood.rottenpotato.main.mvccontollers;

import com.redwood.rottenpotato.main.models.*;
import com.redwood.rottenpotato.main.repositories.*;
import com.redwood.rottenpotato.main.services.MovieService;
import com.redwood.rottenpotato.main.services.PrincipleService;
import com.redwood.rottenpotato.security.model.User;
import com.redwood.rottenpotato.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    @Autowired
    private UserReviewRepository userReviewRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MovieService movieService;

    @Autowired
    private PrincipleService principleService;

    @GetMapping(value = "m/{movieKey}")
    public String movieDetail(@PathVariable("movieKey") String movieKey, Model model, Principal principal) {
        Movie movie = movieRepository.findByMovieKey(movieKey);
        if (movie == null) {
            model.addAttribute("exist", false);
        }
        principleService.principalModel(model, principal);


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
        for (String actorKey : actorKeys) {
            HashMap<String, String> actorMap = new HashMap<>();
            Actor actor = actorRepository.findByActorKey(actorKey);
            if (actor == null) {
                continue;
            }
            actorMap.put("name", actor.getActorName());
            actorMap.put("key", actor.getActorKey());
            actors.add(actorMap);
        }
        model.addAttribute("casts", actors);

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
            model.addAttribute("criticRating", String.format("%.2f", (double) criticScore / criticScoreCount));
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
        model.addAttribute("userScoreCount", userScoreCount);
        if (userScoreCount == 0) {
            model.addAttribute("userRating", "N/A");
        } else {
            model.addAttribute("userRating", String.format("%.2f", (double) userScore / userScoreCount));
        }

        //CriticReviews
        //1. get criticReview based on movieKey(itemKey)
        List<CriticReview> criticReviews = criticReviewRepository.findByItemKey(movie.getMovieKey());

        //2. For the criticReview, create a hashmap of reviews, and put all hashmaps into a list
        List<HashMap> reviews = new ArrayList<>();
        for (CriticReview cr : criticReviews) {
            //one review
            HashMap<String, String> aReview = new HashMap<>();
            if (cr.getReviewRating() == 0) {
                aReview.put("score", "N/A");
            } else {
                aReview.put("score", Integer.toString(cr.getReviewRating()));
            }
            aReview.put("date", cr.getReviewTime());
            aReview.put("content", cr.getReviewContent());
            aReview.put("criticKey", cr.getCriticKey());

            if (this.criticRepository.findByCriticKey(cr.getCriticKey()) != null) {
                String criticName = this.criticRepository.findByCriticKey(cr.getCriticKey()).getCriticName();
                aReview.put("criticName", criticName);
                reviews.add(aReview);
            }
        }
        model.addAttribute("reviews", reviews);

        //user review
        List<UserReview> userReviews = userReviewRepository.findByItemKey(movie.getMovieKey());
        List<HashMap> audienceReviews = new ArrayList<>();
        for (UserReview rev : userReviews) {
            for (UserRating rate : userRatings) {
                if (rev.getUserId() == rate.getUserId()) {
                    HashMap<String, String> uReview = new HashMap<>();
                    User user = userRepository.findById(rev.getUserId());
                    uReview.put("name", user.getFirstName());
                    uReview.put("key", rev.getUserId() + "");
                    uReview.put("score", rate.getRating() + "");
                    uReview.put("content", rev.getContent());
                    uReview.put("reviewId", rev.getId() + "");
                    audienceReviews.add(uReview);
                    break;
                }
            }
        }
        model.addAttribute("audienceReviews", audienceReviews);

        model.addAttribute("movieKey", movieKey);

        String currentDirectory;
        File file = new File("");
        currentDirectory = file.getAbsolutePath();

        currentDirectory += "/rottenpotato/src/main/resources/static/Trailers";

        File folder = new File(currentDirectory.toString());

        File[] listOfFiles = folder.listFiles();
        if (listOfFiles == null) {
            currentDirectory = file.getAbsolutePath();
            currentDirectory += "/src/main/resources/static/Trailers";
            folder = new File(currentDirectory);
            listOfFiles = folder.listFiles();
        }

        List<String> trailerLists = new ArrayList<String>();
        int temp = 0;

        if (listOfFiles != null) {
            for (File fileName : listOfFiles) {
                if (fileName.getName().contains(movieKey)) {
                    temp = 1;
                    trailerLists.add(fileName.getName());
                }
            }
        }
        if (temp == 1) {
            model.addAttribute("hasTrailer", true);
        }
        model.addAttribute("testSamples", trailerLists);

        //related movies
        List<Movie> related = new ArrayList<>();
        if (!movie.getDirector().equals("")) {
            List<String> directornames = Arrays.asList(movie.getDirector().split("\\s*,\\s*"));
            String directorname = directornames.get(0);
            List<Movie> movies = movieRepository.searchByDirector(directorname, movieKey, PageRequest.of(0, 4));
            related = movies;
        }
        model.addAttribute("related", related);
        return "movieInfo.html";

    }


    @GetMapping(value = "m/t/{movieKey}")
    public String movieDetailTopCritic(@PathVariable("movieKey") String movieKey, Model model, Principal principal) {
        Movie movie = movieRepository.findByMovieKey(movieKey);
        if (movie == null) {
            model.addAttribute("exist", false);
        }
        if (principal == null) {
            model.addAttribute("isLogin", false);
        } else {
            model.addAttribute("isLogin", true);
            model.addAttribute("username", principal.getName());
            User user = userRepository.findByEmail(principal.getName());
            model.addAttribute("isAdmin", user.isAdmin());
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
        for (String actorKey : actorKeys) {
            HashMap<String, String> actorMap = new HashMap<>();
            Actor actor = actorRepository.findByActorKey(actorKey);
            if (actor == null) {
                continue;
            }
            actorMap.put("name", actor.getActorName());
            actorMap.put("key", actor.getActorKey());
            actors.add(actorMap);
        }
        model.addAttribute("casts", actors);

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
            model.addAttribute("criticRating", String.format("%.2f", (double) criticScore / criticScoreCount));
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
        model.addAttribute("userScoreCount", userScoreCount);
        if (userScoreCount == 0) {
            model.addAttribute("userRating", "N/A");
        } else {
            model.addAttribute("userRating", String.format("%.2f", (double) userScore / userScoreCount));
        }


        List<Object[]> topCriticReviews = criticReviewRepository.findTop10ByReviewCount();
        List<Critic> topCritics = new ArrayList<>();
        for (Object[] criticReview : topCriticReviews) {
            topCritics.add(criticRepository.findByCriticKey((String) criticReview[0]));
            if (topCritics.size() >= 8) {
                break;
            }
        }

        List<Map> reviews = new ArrayList<>();
        CriticReview crTemp;
        for (Critic criticTemp : topCritics) {
            crTemp = criticReviewRepository.findByItemKeyAndCriticKey(movie.getMovieKey(), criticTemp.getCriticKey());
            if (crTemp != null) {
                HashMap<String, String> aReview = new HashMap<>();
                if (crTemp.getReviewRating() == 0) {
                    aReview.put("score", "N/A");
                } else {
                    aReview.put("score", Integer.toString(crTemp.getReviewRating()));
                }
                aReview.put("date", crTemp.getReviewTime());
                aReview.put("content", crTemp.getReviewContent());
                aReview.put("criticKey", crTemp.getCriticKey());


                aReview.put("criticName", criticTemp.getCriticName());

                reviews.add(aReview);
            }
        }
        model.addAttribute("reviews", reviews);


        //user review
        List<UserReview> userReviews = userReviewRepository.findByItemKey(movie.getMovieKey());
        List<HashMap> audienceReviews = new ArrayList<>();
        for (UserReview rev : userReviews) {
            for (UserRating rate : userRatings) {
                if (rev.getUserId() == rate.getUserId()) {
                    HashMap<String, String> uReview = new HashMap<>();
                    User user = userRepository.findById(rev.getUserId());
                    uReview.put("name", user.getFirstName());
                    uReview.put("key", rev.getUserId() + "");
                    uReview.put("score", rate.getRating() + "");
                    uReview.put("content", rev.getContent());
                    uReview.put("reviewId", rev.getId() + "");
                    audienceReviews.add(uReview);
                    break;
                }
            }
        }
        model.addAttribute("audienceReviews", audienceReviews);

        model.addAttribute("movieKey", movieKey);

        String currentDirectory;
        File file = new File("");
        currentDirectory = file.getAbsolutePath();

        currentDirectory += "/rottenpotato/src/main/resources/static/Trailers";

        File folder = new File(currentDirectory.toString());

        File[] listOfFiles = folder.listFiles();
        if (listOfFiles == null) {
            currentDirectory = file.getAbsolutePath();
            currentDirectory += "/src/main/resources/static/Trailers";
            folder = new File(currentDirectory);
            listOfFiles = folder.listFiles();
        }

        List<String> trailerLists = new ArrayList<String>();
        int temp = 0;

        if (listOfFiles != null) {
            for (File fileName : listOfFiles) {
                if (fileName.getName().contains(movieKey)) {
                    temp = 1;
                    trailerLists.add(fileName.getName());
                }
            }
        }
        if (temp == 1) {
            model.addAttribute("hasTrailer", true);
        }
        model.addAttribute("testSamples", trailerLists);

        return "movieInfoTopCritic.html";

    }

    @GetMapping(value = "m/d/{page}")
    public String movieByDate(@PathVariable("page") int page, Model model, Principal principal) {
        principleService.principalModel(model, principal);
        List<Movie> movies = movieRepository.findComingSoon(PageRequest.of(page, 8));
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
        boolean hasNext = true;
        if (movieRepository.findComingSoon(PageRequest.of(page + 1, 8)).size() <= 0) {
            hasNext = false;
        }
        model.addAttribute("hasNext", hasNext);
        return "movieToDate.html";
    }

    @GetMapping(value = "m/b/{page}")
    public String movieByBox(@PathVariable("page") int page, Model model, Principal principal) {
        principleService.principalModel(model, principal);
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


    @GetMapping(value = "m/o/{page}")
    public String openingThisWeek(@PathVariable("page") int page, Model model, Principal principal) {
        principleService.principalModel(model, principal);
        List<Movie> movies = movieService.openingThisWeekMovie(model, 0);
        List<HashMap> movieList = new ArrayList<>();
        for (Movie movie : movies) {
            HashMap<String, String> movieDetail = new HashMap<>();
            movieDetail.put("name", movie.getName());
            movieDetail.put("key", movie.getMovieKey());
            movieDetail.put("date", movie.getInTheatersTime().toString());
            //TODO
            movieList.add(movieDetail);
        }
        model.addAttribute("movies", movieList);
//        model.addAttribute("page", page);

        return "movieOpeningThisWeek.html";
    }

    @GetMapping(value = "m/oscar/{page}")
    public String oscarWinning(@PathVariable("page") int page, Model model, Principal principal) {
        principleService.principalModel(model, principal);
        List<Movie> movies = movieRepository.findOscarWinningYearDesc(PageRequest.of(page, 8));
        List<HashMap> movieList = new ArrayList<>();
        for (Movie movie : movies) {
            HashMap<String, String> movieDetail = new HashMap<>();
            movieDetail.put("name", movie.getName());
            movieDetail.put("key", movie.getMovieKey());
            movieDetail.put("date", Long.toString(movie.getOscarBestPictureYear()));
            //TODO
            movieList.add(movieDetail);
        }
        model.addAttribute("movies", movieList);
        model.addAttribute("page", page);

        boolean hasNext = true;
        if (movieRepository.findOscarWinningYearDesc(PageRequest.of(page, 8)).size() <= 0) {
            hasNext = false;
        }
        model.addAttribute("hasNext", hasNext);
        return "movieToOscar.html";

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


    public String[] castsTransfer(String casts) {
        String castsArray[] = casts.split(",");
        return castsArray;
    }

    public List<Map> castNamesAndKeys(String[] castsArr) {
        List<Map> tempList = new ArrayList<>();
        for (int i = 0; i <= castsArr.length - 1 && i < 5; i++) {

            Map<String, String> map = new HashMap<>();
            map.put("actorKey", castsArr[i]);
            map.put("actorName", toCastName(castsArr[i]));
            tempList.add(map);
        }
        return tempList;
    }

    //6 actors in maximum
    public List<Map> getActorNamesAndNamesByKeys(String[] castsArr) {
        List<Map> tempList = new ArrayList<>();
        for (int i = 0; i <= castsArr.length - 1 && i < 7; i++) {
            Map<String, String> map = new HashMap<>();

            //if this actor key is not empty
            if (this.actorRepository.findByActorKey(castsArr[i]) != null) {
                map.put("actorKey", castsArr[i]);
                map.put("actorName", this.actorRepository.findByActorKey(castsArr[i]).getActorName());
                tempList.add(map);
            }

        }
        return tempList;
    }

    public String toCastName(String cast) {
        String temp = cast.replace("_", " ");
        temp = temp.replace("-", " ");
        return temp;
    }
}
