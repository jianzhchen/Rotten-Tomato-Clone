package com.redwood.rottenpotato.main.mvccontollers;

import com.redwood.rottenpotato.main.models.*;
import com.redwood.rottenpotato.main.repositories.*;
import com.redwood.rottenpotato.security.model.User;
import com.redwood.rottenpotato.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/1")
public class ProfileMvcController {
    @Autowired
    public UserRepository userRepository;
    @Autowired
    private UserReviewRepository userReviewRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TVRepository tVRepository;
    @Autowired
    private UserRatingRepository userRatingRepository;
    @Autowired
    private WantToSeeRepository wantToSeeRepository;
    @Autowired
    private NotInterestedRepository notInterestedRepository;
    @Autowired
    private FollowRepository followRepository;

    @RequestMapping("/me")
    public String profile(Model model, Principal principal) {
        String userEmail = principal.getName();
        User user = userRepository.findByEmail(userEmail);

        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("lastName", user.getLastName());
        model.addAttribute("email", user.getEmail());
        List<HashMap> reviews = new ArrayList<>();
        List<HashMap> ratings = new ArrayList<>();
        List<HashMap> notinteresteds = new ArrayList<>();
        List<HashMap> wanttosees = new ArrayList<>();
        List<HashMap> following = new ArrayList<>();
        List<HashMap> followby = new ArrayList<>();

        //1. reviews
        for (UserReview review : userReviewRepository.findByUserId(user.getId())) {
            HashMap<String, String> map = new HashMap<>();
            String itemKey = review.getItemKey();
            Movie movie = movieRepository.findByMovieKey(itemKey);
            TV tv = tVRepository.findByTVKey(itemKey);
            if (movie != null) {
                map.put("url", "/m/" + movie.getMovieKey());
                map.put("key", movie.getMovieKey());
                map.put("name", movie.getName());
            } else {
                map.put("url", "/t/" + tv.getTVKey());
                map.put("key", tv.getTVKey());
                map.put("name", tv.getTVName());
            }
            map.put("content", review.getContent());
            reviews.add(map);
        }

        //2. ratings
        for (UserRating userRating : userRatingRepository.findByUserId(user.getId())) {
            HashMap<String, String> map = new HashMap<>();
            String itemKey = userRating.getItemKey();
            Movie movie = movieRepository.findByMovieKey(itemKey);
            TV tv = tVRepository.findByTVKey(itemKey);
            if (movie != null) {
                map.put("url", "/m/" + movie.getMovieKey());
                map.put("name", movie.getName());
            } else {
                map.put("url", "/t/" + tv.getTVKey());
                map.put("name", tv.getTVName());
            }
            map.put("score", Integer.toString(userRating.getRating()));
            ratings.add(map);
        }

        //3. wantToSees
        for (WantToSee wantToSee : wantToSeeRepository.findByUserId(user.getId())) {
            HashMap<String, String> map = new HashMap<>();
            String itemKey = wantToSee.getItemKey();
            Movie movie = movieRepository.findByMovieKey(itemKey);
            TV tv = tVRepository.findByTVKey(itemKey);
            if (movie != null) {
                map.put("url", "/m/" + movie.getMovieKey());
                map.put("key", movie.getMovieKey());
                map.put("name", movie.getName());
            } else {
                map.put("url", "/t/" + tv.getTVKey());
                map.put("key", tv.getTVKey());
                map.put("name", tv.getTVName());
            }
            wanttosees.add(map);
        }

        //4. notInteresteds
        for (NotInterested notInterested : notInterestedRepository.findByUserId(user.getId())) {
            HashMap<String, String> map = new HashMap<>();
            String itemKey = notInterested.getItemKey();
            Movie movie = movieRepository.findByMovieKey(itemKey);
            TV tv = tVRepository.findByTVKey(itemKey);
            if (movie != null) {
                map.put("url", "/m/" + movie.getMovieKey());
                map.put("key", movie.getMovieKey());
                map.put("name", movie.getName());
            } else {
                map.put("url", "/t/" + tv.getTVKey());
                map.put("key", tv.getTVKey());
                map.put("name", tv.getTVName());
            }
            notinteresteds.add(map);
        }

        //5. Following
        for (Follow follow : followRepository.findByUserIdFrom(user.getId())) {
            HashMap<String, String> map = new HashMap<>();
            long uid = follow.getUserIdTo();
            User u = userRepository.findById(uid);
            map.put("key",u.getId()+"");
            map.put("name",u.getFirstName()+" "+u.getLastName());
            following.add(map);
        }

        //6. Followers

        for (Follow follow : followRepository.findByUserIdTo(user.getId())) {
            HashMap<String, String> map = new HashMap<>();
            long uid = follow.getUserIdFrom();
            User u = userRepository.findById(uid);
            map.put("key",u.getId()+"");
            map.put("name",u.getFirstName()+" "+u.getLastName());
            followby.add(map);
        }

        model.addAttribute("reviews", reviews);
        model.addAttribute("ratings", ratings);
        model.addAttribute("notInteresteds", notinteresteds);
        model.addAttribute("wantToSees", wanttosees);
        model.addAttribute("followings", following);
        model.addAttribute("followers", followby);
        model.addAttribute("numberOfFollowers", followby.size());

        return "profilePage.html";
    }
    @RequestMapping("/accountInfo")
    public String profile() {
        return "accountInfo.html";
    }
}
