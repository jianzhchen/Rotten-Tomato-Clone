package com.redwood.rottenpotato.main.mvccontollers;

import com.redwood.rottenpotato.main.models.*;
import com.redwood.rottenpotato.main.repositories.*;
import com.redwood.rottenpotato.main.services.PrincipleService;
import com.redwood.rottenpotato.security.model.User;
import com.redwood.rottenpotato.security.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.*;

@Controller
public class TVMvcController {
    @Autowired
    public TVRepository tVRepository;
    @Autowired
    public ActorRepository actorRepository;
    @Autowired
    public CriticReviewRepository criticReviewRepository;
    @Autowired
    public UserRatingRepository userRatingRepository;
    @Autowired
    private CriticRepository criticRepository;
    @Autowired
    private UserReviewRepository userReviewRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PrincipleService principleService;
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @GetMapping(value = "t/{TVKey}")
    public String tvDetail(@PathVariable("TVKey") String tVKey, Model model,  Principal principal) {


        TV tv = tVRepository.findByTVKey(tVKey);


        principleService.principalModel(model, principal);
        if (tv == null) {
            model.addAttribute("exist", false);
        }
        model.addAttribute("tvKey", tv.getTVKey());
        model.addAttribute("name", tv.getTVName());
        model.addAttribute("info", tv.getTVInfo());
        model.addAttribute("genre", tv.getTVGenre());
        model.addAttribute("network", tv.getTVNetwork());
        model.addAttribute("date", tv.getTVDate());

        String cast = tv.getTVCast();
        List<String> actorKeys = Arrays.asList(cast.split("\\s*,\\s*"));
        List<HashMap> actors = new ArrayList<>();
        for (String actorKey : actorKeys) {
            HashMap<String, String> actorMap = new HashMap<>();
            Actor actor = actorRepository.findByActorKey(actorKey);
            if(actor==null){
                continue;
            }
            actorMap.put("name", actor.getActorName());
            actorMap.put("key", actor.getActorKey());
            actors.add(actorMap);
        }

        model.addAttribute("casts", actors);

        List<CriticReview> crs = criticReviewRepository.findByItemKey(tv.getTVKey());
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
        List<UserRating> userRatings = userRatingRepository.findByItemKey(tv.getTVKey());
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
            model.addAttribute("userRating", String.format("%.2f", (double)userScore / userScoreCount));
        }

        //CriticReviews from MovieMvcController
        //1. get criticReview based on movieKey(itemKey)
        List<CriticReview> criticReviews1 = criticReviewRepository.findByItemKey(tv.getTVKey());

        //2. For the criticReview, create a hashmap of reviews, and put all hashmaps into a list
        List<HashMap> reviews = new ArrayList<>();
        for (CriticReview cr : criticReviews1)
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

        // Audience Reviews
        List<UserReview> userReviews = userReviewRepository.findByItemKey(tv.getTVKey());
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
                    uReview.put("reviewId",rev.getId()+"");
                    audienceReviews.add(uReview);
                    break;
                }
            }
        }
        model.addAttribute("audienceReviews", audienceReviews);
        //TODO poster
        return "tvInfo.html";
    }

    @GetMapping(value = "t/t/{TVKey}")
    public String tvDetailTopCritic(@PathVariable("TVKey") String tVKey, Model model,  Principal principal) {


        TV tv = tVRepository.findByTVKey(tVKey);


        principleService.principalModel(model, principal);
        if (tv == null) {
            model.addAttribute("exist", false);
        }
        model.addAttribute("tvKey", tv.getTVKey());
        model.addAttribute("name", tv.getTVName());
        model.addAttribute("info", tv.getTVInfo());
        model.addAttribute("genre", tv.getTVGenre());
        model.addAttribute("network", tv.getTVNetwork());
        model.addAttribute("date", tv.getTVDate());

        String cast = tv.getTVCast();
        List<String> actorKeys = Arrays.asList(cast.split("\\s*,\\s*"));
        List<HashMap> actors = new ArrayList<>();
        for (String actorKey : actorKeys) {
            HashMap<String, String> actorMap = new HashMap<>();
            Actor actor = actorRepository.findByActorKey(actorKey);
            if(actor==null){
                continue;
            }
            actorMap.put("name", actor.getActorName());
            actorMap.put("key", actor.getActorKey());
            actors.add(actorMap);
        }

        model.addAttribute("casts", actors);

        List<CriticReview> crs = criticReviewRepository.findByItemKey(tv.getTVKey());
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
        List<UserRating> userRatings = userRatingRepository.findByItemKey(tv.getTVKey());
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
            model.addAttribute("userRating", String.format("%.2f", (double)userScore / userScoreCount));
        }






        List<Object[]> topCriticReviews = criticReviewRepository.findTop10ByReviewCount();
        List<Critic> topCritics = new ArrayList<>();
        for (Object[] criticReview : topCriticReviews) {
            topCritics.add(criticRepository.findByCriticKey((String) criticReview[0]));
            if (topCritics.size() >= 8) {
                break;
            }
        }

        List<Map> reviews =  new ArrayList<>();
        CriticReview crTemp;
        for(Critic criticTemp: topCritics){
            crTemp = criticReviewRepository.findByItemKeyAndCriticKey( tv.getTVKey(), criticTemp.getCriticKey());
            if(crTemp != null){
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


        // Audience Reviews
        List<UserReview> userReviews = userReviewRepository.findByItemKey(tv.getTVKey());
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
                    uReview.put("reviewId",rev.getId()+"");
                    audienceReviews.add(uReview);
                    break;
                }
            }
        }
        model.addAttribute("audienceReviews", audienceReviews);
        //TODO poster
        return "tvInfoTopCritic.html";
    }

    @GetMapping(value = "t/l/{page}")
    public String movieByDate(@PathVariable("page") int page, Model model, Principal principal) {
        principleService.principalModel(model, principal);
        List<TV> tvs = tVRepository.findTop10ByOrderByTVDateDateDesc(PageRequest.of(page, 8));
        List<HashMap> tvList = new ArrayList<>();
        for (TV tv : tvs) {
            HashMap<String, String> movieDetail = new HashMap<>();
            movieDetail.put("name", tv.getTVName());
            movieDetail.put("key", tv.getTVKey());
            movieDetail.put("date", tv.getTVDate());
            //TODO
            tvList.add(movieDetail);
        }
        model.addAttribute("TVs", tvList);
        model.addAttribute("page", page);
        return "tv.html";
    }


}
