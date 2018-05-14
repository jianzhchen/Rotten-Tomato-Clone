package com.redwood.rottenpotato.main.mvccontollers;

import com.redwood.rottenpotato.main.models.*;
import com.redwood.rottenpotato.main.repositories.*;
import com.redwood.rottenpotato.main.services.PrincipleService;
import com.redwood.rottenpotato.security.model.User;
import com.redwood.rottenpotato.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserMvcController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FollowRepository followRepository;
    @Autowired
    private FCountRepository fCountRepository;
    @Autowired
    private WantToSeeRepository wantToSeeRepository;
    @Autowired
    private UserReviewRepository userReviewRepository;
    @Autowired
    private UserRatingRepository userRatingRepository;
    @Autowired
    private NotInterestedRepository notInterestedRepository;

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TVRepository tVRepository;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private PrincipleService principleService;

    @RequestMapping("/u/{userId}")
    public String userPage(@PathVariable("userId") long userId, Model model, Principal principal) {
        boolean isLogin = false;
        if (!(principal == null)) {
            isLogin = true;
        }
        principleService.principalModel(model, principal);
//
        if (isLogin) {
            User loginUser = userRepository.findByEmail(principal.getName());
            if (userId == loginUser.getId()) {
                return "redirect:/1/me";
            }
        }

        User user = userRepository.findById(userId);
        if (user == null) {
            model.addAttribute("error", "user not found");
        } else {
            if (!user.isOpenProfile()) {
                model.addAttribute("openProfile", false);
                return "user.html";
            }
            model.addAttribute("openProfile", true);

            List<Follow> followBy = followRepository.findByUserIdTo(user.getId());
            model.addAttribute("followerCount", followBy.size());
            List<WantToSee> wantToSeeList = wantToSeeRepository.findByUserId(user.getId());
            Map<String, String> movieMap = new HashMap<>();
            Map<String, String> tVMap = new HashMap<>();
            for (WantToSee wantToSee : wantToSeeList) {

                Movie movie = movieRepository.findByMovieKey(wantToSee.getItemKey());
                TV tV = tVRepository.findByTVKey(wantToSee.getItemKey());
                if (movie != null) {
                    movieMap.put("key", movie.getMovieKey());
                    movieMap.put("name", movie.getName());
                } else {
                    movieMap.put("key", tV.getTVKey());
                    movieMap.put("name", tV.getTVName());
                }
            }
            model.addAttribute("WantToSeeListMovie", movieMap);
            model.addAttribute("WantToSeeListTV", tVMap);


            model.addAttribute("firstName", user.getFirstName());
            model.addAttribute("lastName", user.getLastName());
            model.addAttribute("email", user.getEmail());
            model.addAttribute("userId", user.getId());

            List<HashMap> reviews = new ArrayList<>();
            List<HashMap> ratings = new ArrayList<>();
            List<HashMap> notinteresteds = new ArrayList<>();
            List<HashMap> wanttosees = new ArrayList<>();
            List<HashMap> following = new ArrayList<>();
            List<HashMap> followby = new ArrayList<>();

            //1. reviews
            List<UserRating> userRatings = userRatingRepository.findByUserId(user.getId());
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
                for (UserRating rate : userRatings) {
                    if (review.getUserId() == rate.getUserId() && review.getItemKey().equals(rate.getItemKey())) {
                        map.put("rate", rate.getRating() + "");
                        map.put("ratingId", rate.getId() + "");
                        break;
                    }
                }
                map.put("content", review.getContent());
                map.put("reviewId", review.getId() + "");
                reviews.add(map);
            }

            //2. ratings
//            for (UserRating userRating : userRatingRepository.findByUserId(user.getId())) {
//                HashMap<String, String> map = new HashMap<>();
//                String itemKey = userRating.getItemKey();
//                Movie movie = movieRepository.findByMovieKey(itemKey);
//                TV tv = tVRepository.findByTVKey(itemKey);
//                if (movie != null) {
//                    map.put("url", "/m/" + movie.getMovieKey());
//                    map.put("name", movie.getName());
//                } else {
//                    map.put("url", "/t/" + tv.getTVKey());
//                    map.put("name", tv.getTVName());
//                }
//                map.put("score", Integer.toString(userRating.getRating()));
//                ratings.add(map);
//            }

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
                map.put("key", uid + "");
                map.put("name", u.getFirstName() + " " + u.getLastName());
                following.add(map);
            }

            //6. Followers
            for (Follow follow : followRepository.findByUserIdTo(user.getId())) {
                HashMap<String, String> map = new HashMap<>();
                long uid = follow.getUserIdFrom();
                User u = userRepository.findById(uid);
                map.put("key", uid + "");
                map.put("name", u.getFirstName() + " " + u.getLastName());
                followby.add(map);
            }

            if (isLogin) {
                //For follow/unfollow button
                if (followby.isEmpty()) {
                    model.addAttribute("followStatus", "Follow");
                    model.addAttribute("followButtonClass", "btn btn-sm btn-success");
                } else {
                    for (Follow follow : followRepository.findByUserIdTo(user.getId())) {
                        //uid = fromId
                        long uid = follow.getUserIdFrom();
                        String currentEmail = principal.getName();
                        //toUser
                        User currentUser = this.userRepository.findByEmail(currentEmail);
                        if (uid == currentUser.getId()) {
                            model.addAttribute("followStatus", "unFollow");
                            model.addAttribute("followButtonClass", "btn btn-sm btn-danger");
                            //If current user follows this found, break
                            break;
                        } else {
                            model.addAttribute("followStatus", "Follow");
                            model.addAttribute("followButtonClass", "btn btn-sm btn-success");
                        }

                    }
                }
            }

            model.addAttribute("reviews", reviews);
            model.addAttribute("ratings", ratings);
            model.addAttribute("notInteresteds", notinteresteds);
            model.addAttribute("wantToSees", wanttosees);
            model.addAttribute("followings", following);
            model.addAttribute("followers", followby);
            model.addAttribute("numberOfFollowers", followby.size());
        }


        String sqlQuery = "SELECT\n" +
                "  FIND_IN_SET(follower_count, (\n" +
                "    SELECT GROUP_CONCAT(follower_count ORDER BY follower_count DESC)\n" +
                "    FROM fcount)\n" +
                "  ) AS rank\n" +
                "FROM fcount\n" +
                "WHERE user_id = :userId";
        Query query = entityManager.createNativeQuery(sqlQuery);
        query.setParameter("userId", user.getId());
        List<BigInteger> resultList = query.getResultList();
        if (resultList.size() > 0) {
            BigInteger result = resultList.get(0);
            model.addAttribute("followerRank", result.intValue());
        } else {
            model.addAttribute("followerRank", "n/s");
        }
        return "user.html";
    }
}
