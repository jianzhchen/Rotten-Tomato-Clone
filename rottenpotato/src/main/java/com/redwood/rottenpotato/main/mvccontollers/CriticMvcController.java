package com.redwood.rottenpotato.main.mvccontollers;

import com.redwood.rottenpotato.main.DTO.TopCriticDTO;
import com.redwood.rottenpotato.main.models.*;
import com.redwood.rottenpotato.main.repositories.*;
import com.redwood.rottenpotato.main.services.CriticService;
import com.redwood.rottenpotato.main.services.PrincipleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PrincipleService principleService;
    @RequestMapping("/critic/{criticKey}")
    public String criticPage(@PathVariable("criticKey") String criticKey, Model model, Principal principal,
                             @RequestParam(value = "page", defaultValue = "0") int page,
                             @RequestParam(value = "order", defaultValue = "0") int order) {
        principleService.principalModel(model, principal);
        Critic critic = criticRepository.findByCriticKey(criticKey);
        model.addAttribute("name", critic.getCriticName());
        model.addAttribute("info", critic.getCriticInfo());
        boolean hasNext = true;
        List<CriticReview> criticReviews;
        if (order == 1) {
            criticReviews = criticReviewRepository.findTop10ByCriticKeyAndReviewRatingNotOrderByReviewRatingDesc(criticKey, 0, PageRequest.of(page, 10));
            if (criticReviewRepository.findTop10ByCriticKeyAndReviewRatingNotOrderByReviewRatingDesc(criticKey, 0, PageRequest.of(page + 1, 10)).size() <= 0) {
                hasNext = false;
            }
        } else if (order == 2) {
            criticReviews = criticReviewRepository.findTop10ByCriticKeyAndReviewRatingNotOrderByReviewRatingAsc(criticKey, 0, PageRequest.of(page, 10));
            if (criticReviewRepository.findTop10ByCriticKeyAndReviewRatingNotOrderByReviewRatingAsc(criticKey, 0, PageRequest.of(page + 1, 10)).size() <= 0) {
                hasNext = false;
            }
        } else {
            criticReviews = criticReviewRepository.findTop10ByCriticKeyOrderByReviewTimeDateDesc(criticKey, PageRequest.of(page, 10));
            if (criticReviewRepository.findTop10ByCriticKeyOrderByReviewTimeDateDesc(criticKey, PageRequest.of(page + 1, 10)).size() <= 0) {
                hasNext = false;
            }
        }

        List<HashMap> reviews = new ArrayList<>();
        for (CriticReview cv : criticReviews) {
            HashMap<String, String> map = new HashMap<>();
            String itemKey = cv.getItemKey();
            Movie movie = movieRepository.findByMovieKey(itemKey);
            TV tv = tVRepository.findByTVKey(itemKey);
            if (movie != null) {
                map.put("url", "/m/" + itemKey);
                map.put("itemName", movie.getName());
                map.put("score", Integer.toString(cv.getReviewRating()));
                map.put("date", cv.getReviewTimeDate() + "");
                map.put("content", cv.getReviewContent());
                reviews.add(map);
            } else if (tv != null) {
                map.put("url", "/t/" + itemKey);
                map.put("itemName", tv.getTVName());
                map.put("score", Integer.toString(cv.getReviewRating()));
                map.put("date", cv.getReviewTimeDate() + "");
                map.put("content", cv.getReviewContent());
                reviews.add(map);
            }
        }
        model.addAttribute("hasNext", hasNext);
        model.addAttribute("criticKey", criticKey);
        model.addAttribute("recentReviews", reviews);
        model.addAttribute("page", page);
        model.addAttribute("order",order);
        return "criticPage.html";
    }


    @RequestMapping("/critic_t/{page}")
    public String criticReviewLatest(@PathVariable("page") int page, Model model, Principal principal) {
        principleService.principalModel(model, principal);
        List<CriticReview> criticReviews = criticReviewRepository.findTop10ByOrderByReviewTimeDateDesc(PageRequest.of(page, 10));
        List<HashMap> reviews = new ArrayList<>();
        for (CriticReview criticReview : criticReviews) {
            HashMap<String, String> map = new HashMap<>();
            String itemKey = criticReview.getItemKey();
            Movie movie = movieRepository.findByMovieKey(itemKey);
            TV tv = tVRepository.findByTVKey(itemKey);
            if (movie != null) {
                map.put("itemName", movie.getName());
                map.put("url", "/m/" + movie.getMovieKey());
            } else if (tv != null) {
                map.put("itemName", tv.getTVName());
                map.put("url", "/t/" + tv.getTVKey());
            } else {
                continue;
            }
            map.put("criticKey", criticReview.getCriticKey());
            Critic critic = criticRepository.findByCriticKey(criticReview.getCriticKey());
            map.put("name", critic.getCriticName());
            map.put("score", Integer.toString(criticReview.getReviewRating()));
            map.put("date", criticReview.getReviewTimeDate() + "");
            map.put("content", criticReview.getReviewContent());
            reviews.add(map);
        }
        model.addAttribute("reviews", reviews);
        return "criticReviews.html";
    }

    @RequestMapping("/critic_all/{page}")
    public String criticAll(@PathVariable("page") int page, Model model, Principal principal) {
        principleService.principalModel(model, principal);
        List<Critic> critics = criticRepository.findTop10ByOrderByCriticNameAsc(PageRequest.of(page, 10));
        model.addAttribute("critics", critics);
        model.addAttribute("page", page);
        List<Object[]> criticReviews = criticReviewRepository.findTop10ByReviewCount();
        List<Critic> topCritics = new ArrayList<>();
        for (Object[] criticReview : criticReviews) {
            topCritics.add(criticRepository.findByCriticKey((String) criticReview[0]));
            if (topCritics.size() >= 8) {
                break;
            }
        }
        model.addAttribute("topCritics", topCritics);
        return "critic.html";
    }


    @GetMapping(value = "/critics")
    public String getCriticPage(Model model, Principal principal) {
        principleService.principalModel(model, principal);
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
