package com.redwood.rottenpotato.main.mvccontollers;

import com.redwood.rottenpotato.main.models.*;
import com.redwood.rottenpotato.main.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/critic/{criticKey}")
    public String criticPage(@PathVariable("criticKey") String criticKey, Model model) {
        Critic critic = criticRepository.findByCriticKey(criticKey);
        model.addAttribute("name", critic.getCriticName());
        model.addAttribute("info", critic.getCriticInfo());
        List<CriticReview> criticReviews = criticReviewRepository.findTop10ByCriticKeyOrderByReviewTimeDateDesc(criticKey, PageRequest.of(0, 10));
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
                map.put("date", cv.getReviewTime());
                map.put("content", cv.getReviewContent());
            } else if (tv != null) {
                map.put("url", "/t/" + itemKey);
                map.put("itemName", tv.getTVName());
                map.put("score", Integer.toString(cv.getReviewRating()));
                map.put("date", cv.getReviewTime());
                map.put("content", cv.getReviewContent());
            }
        }
        model.addAttribute("recentReviews", reviews);
        return "criticPage.html";
    }


    @RequestMapping("/critic_t/{page}")
    public String criticReviewLatest(@PathVariable("page") int page, Model model) {
        List<CriticReview> criticReviews = criticReviewRepository.findTop10ByOrderByReviewTimeDateDesc(PageRequest.of(page, 10));
        List<HashMap> reviews = new ArrayList<>();
        for (CriticReview criticReview : criticReviews) {
            HashMap<String, String> map = new HashMap<>();
            map.put("criticKey", criticReview.getCriticKey());
            Critic critic = criticRepository.findByCriticKey(criticReview.getCriticKey());
            map.put("name", critic.getCriticName());
            map.put("score", Integer.toString(criticReview.getReviewRating()));
            map.put("content", criticReview.getReviewContent());
        }
        model.addAttribute("reviews", reviews);
        return "criticReviews.html";
    }

    @RequestMapping("/critic_all/{page}")
    public String criticAll(@PathVariable("page") int page, Model model) {
        List<Critic> critics = criticRepository.findTopByOrderByCriticNameDesc(PageRequest.of(page, 10));
        model.addAttribute("critics", critics);
        model.addAttribute("page",page);
        return "critic.html";
    }
}
