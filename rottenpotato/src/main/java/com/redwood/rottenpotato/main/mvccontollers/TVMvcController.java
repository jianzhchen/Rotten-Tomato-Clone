package com.redwood.rottenpotato.main.mvccontollers;

import com.redwood.rottenpotato.main.models.*;
import com.redwood.rottenpotato.main.repositories.ActorRepository;
import com.redwood.rottenpotato.main.repositories.CriticReviewRepository;
import com.redwood.rottenpotato.main.repositories.TVRepository;
import com.redwood.rottenpotato.main.repositories.UserRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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

    @GetMapping(value = "t/{TVKey}/{season}")
    public String tvDetail(@PathVariable("TVKey") String tVKey, @PathVariable("season") String seasonKey, Model model) {
        TV tv = tVRepository.findByTVKey(tVKey + "/" + seasonKey);
        if (tv == null) {
            model.addAttribute("exist", false);
        }
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
            actorMap.put("name", actor.getActorName());
            actorMap.put("key", actor.getActorName());
            actors.add(actorMap);
        }

        model.addAttribute("actors", actors);
        List<CriticReview> crs = criticReviewRepository.findByItemKey(tv.getTVKey());
        int criticScore = 0;
        int criticScoreCount = 0;
        for (CriticReview criticReview : crs) {
            if (criticReview.getReviewRating() != 0) {
                criticScore = criticScore + criticReview.getReviewRating();
                criticScoreCount++;
            }
        }
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
        if (userScoreCount == 0) {
            model.addAttribute("userRating", "N/A");
        } else {
            model.addAttribute("userRating", String.format("%.2f", (double)userScore / userScoreCount));
        }

        //TODO poster
        return "tvInfo.html";
    }
}
