package com.redwood.rottenpotato.main.mvccontollers;

import com.redwood.rottenpotato.main.models.*;
import com.redwood.rottenpotato.main.repositories.*;
import com.redwood.rottenpotato.main.services.CriticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class CriticMvcController {

    @Autowired
    private CriticRepository criticRepository;
    @Autowired
    private CriticReviewRepository criticReviewRepository;
    @Autowired
    private TVRepository tVRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private CriticService criticService;

    @RequestMapping("/critic/{criticKey}")
    public String criticPage(@PathVariable("criticKey") String criticKey, Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("isLogin", false);
        } else {
            model.addAttribute("isLogin", true);
            model.addAttribute("username", principal.getName());
        }
        Critic critic = criticRepository.findByCriticKey(criticKey);
        model.addAttribute("name", critic.getCriticName());
        model.addAttribute("info", critic.getCriticInfo());
        List<CriticReview> criticReviews = criticReviewRepository.findTop10ByCriticKeyOrderByReviewTimeDateDesc(criticKey, PageRequest.of(0, 10));
//        System.out.println(criticReviews.get(0).getReviewTime());
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println();

        List<HashMap> reviews = new ArrayList<>();
        for (CriticReview cv : criticReviews) {
            HashMap<String, String> map = new HashMap<>();
            String itemKey = cv.getItemKey();
            Movie movie = movieRepository.findByMovieKey(itemKey);

//            TV tv = tVRepository.findByTVKey(itemKey);
            if (movie != null) {
                map.put("url", "/m/" + itemKey);
                map.put("itemKey", itemKey);
                map.put("itemName", movie.getName());
                map.put("score", Integer.toString(cv.getReviewRating()));
                map.put("date", cv.getReviewTime());
                map.put("content", cv.getReviewContent());
                reviews.add(map);
            }
//            else if (tv != null) {
//                map.put("url", "/t/" + itemKey);
//                map.put("itemName", tv.getTVName());
//                map.put("score", Integer.toString(cv.getReviewRating()));
//                map.put("date", cv.getReviewTime());
//                map.put("content", cv.getReviewContent());
//            }
        }
        model.addAttribute("criticKey", criticKey);
        model.addAttribute("recentReviews", reviews);
        return "criticPage.html";
    }


    @RequestMapping("/critic_t/{page}")
    public String criticReviewLatest(@PathVariable("page") int page, Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("isLogin", false);
        } else {
            model.addAttribute("isLogin", true);
            model.addAttribute("username", principal.getName());
        }
        List<CriticReview> criticReviews = criticReviewRepository.findTop10ByOrderByReviewTimeDateDesc(PageRequest.of(page, 10));
        List<HashMap> reviews = new ArrayList<>();
        for (CriticReview criticReview : criticReviews) {
            HashMap<String, String> map = new HashMap<>();
            String itemKey = criticReview.getItemKey();
            Movie movie = movieRepository.findByMovieKey(itemKey);
            TV tv = tVRepository.findByTVKey(itemKey);
            if (movie != null) {
                map.put("itemName", movie.getName());
            } else if (tv != null) {
                map.put("itemName", tv.getTVName());
            } else {
                continue;
            }
            map.put("criticKey", criticReview.getCriticKey());
            Critic critic = criticRepository.findByCriticKey(criticReview.getCriticKey());
            map.put("name", critic.getCriticName());
            map.put("score", Integer.toString(criticReview.getReviewRating()));
            map.put("content", criticReview.getReviewContent());
            reviews.add(map);
        }
        model.addAttribute("reviews", reviews);
        return "criticReviews.html";
    }

    @RequestMapping("/critic_all/{page}")
    public String criticAll(@PathVariable("page") int page, Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("isLogin", false);
        } else {
            model.addAttribute("isLogin", true);
            model.addAttribute("username", principal.getName());
        }
        List<Critic> critics = criticRepository.findTop10ByOrderByCriticNameAsc(PageRequest.of(page, 10));
        model.addAttribute("critics", critics);
        model.addAttribute("page", page);
        List<CriticReview> criticReviews = criticReviewRepository.findTop10ByReviewCount();
        List<Critic> topCritics = new ArrayList<>();
        for (CriticReview criticReview : criticReviews) {
            topCritics.add(criticRepository.findByCriticKey(criticReview.getCriticKey()));
            if (topCritics.size() >= 8) {
                break;
            }
        }
        model.addAttribute("topCritics", topCritics);
        return "critic.html";
    }


    @GetMapping(value = "/critics")
    public String getCriticPage(Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("isLogin", false);
        } else {
            model.addAttribute("isLogin", true);
            model.addAttribute("username", principal.getName());
        }
        model.addAttribute("critics", criticService.getAllCriticNamesKeys(model, 0));
        return "critic.html";
    }

//    @GetMapping(value = "critic/{criticKey}")
//    public String getCriticPage(@PathVariable("criticKey") String criticKey, Model model) {
//
//        Critic critic = criticRepository.findByCriticKey(criticKey);
//        if(critic != null){
//            model.addAttribute("name", critic.getCriticName());
//        }
//
//
////        Movie movie = movieRepository.findByMovieKey(movieKey);
////        if (movie == null) {
////            model.addAttribute("exist", false);
////        }
////        model.addAttribute("name", movie.getName());
////        model.addAttribute("info", movie.getInfo());
////        model.addAttribute("movieRating", movie.getRating());
////        model.addAttribute("genre", movie.getGenre());
////        model.addAttribute("director", movie.getDirector());
////        model.addAttribute("writer", movie.getWriter());
////        model.addAttribute("inTheater", movie.getInTheaters());
////        model.addAttribute("onDisc", movie.getOnDisc());
////        model.addAttribute("boxOffice", boxOfficeTransfer(movie.getBoxOffice()));
////        model.addAttribute("runTime", movie.getRunTime());
////        model.addAttribute("studio", movie.getStudio());
////        String cast = movie.getCast();
////        List<String> actorKeys = Arrays.asList(cast.split("\\s*,\\s*"));
////        List<HashMap> actors = new ArrayList<>();
////        for (String actorKey : actorKeys) {
////            HashMap<String, String> actorMap = new HashMap<>();
////            Actor actor = actorRepository.findByActorKey(actorKey);
////            actors.add(actorMap);
////        }
////        model.addAttribute("actors", actors);
////        List<CriticReview> crs = criticReviewRepository.findByItemKey(movieKey);
////        int criticScore = 0;
////        int criticScoreCount = 0;
////        for (CriticReview criticReview : crs) {
////            if (criticReview.getReviewRating() != 0) {
////                criticScore = criticScore + criticReview.getReviewRating();
////                criticScoreCount++;
////            }
////        }
////        if (criticScoreCount == 0) {
////            model.addAttribute("criticRating", "N/A");
////        } else {
////            model.addAttribute("criticRating", String.format("%.2f", (double)criticScore / criticScoreCount));
////        }
////
////        List<UserRating> userRatings = userRatingRepository.findByItemKey(movieKey);
////        int userScore = 0;
////        int userScoreCount = 0;
////        for (UserRating userRating : userRatings) {
////            if (userRating.getRating() != 0) {
////                userScore = userScore + userRating.getRating();
////                userScoreCount++;
////            }
////        }
////        if (userScoreCount == 0) {
////            model.addAttribute("userRating", "N/A");
////        } else {
////            model.addAttribute("userRating", String.format("%.2f", (double)userScore / userScoreCount));
////        }
//        return "criticPage.html";
//
//    }
}
