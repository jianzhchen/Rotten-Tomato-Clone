package com.redwood.rottenpotato.main.mvccontollers;

import com.redwood.rottenpotato.main.models.Actor;
import com.redwood.rottenpotato.main.models.Follow;
import com.redwood.rottenpotato.main.models.Movie;
import com.redwood.rottenpotato.main.models.WantToSee;
import com.redwood.rottenpotato.main.repositories.*;
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
    private MovieRepository movieRepository;
    @Autowired
    private EntityManager entityManager;

    @RequestMapping("/u/{userId}")
    public String userPage(@PathVariable("userId") long userId, Model model) {
        User user = userRepository.findById(userId);
        if (user == null) {
            model.addAttribute("error", "user not found");
        } else {
            List<Follow> followBy = followRepository.findByUserIdTo(user.getId());
            model.addAttribute("followerCount", followBy.size());
            List<WantToSee> wantToSeeList = wantToSeeRepository.findByUserId(user.getId());
            Map<String, String> movieMap = new HashMap<>();
            for (WantToSee wantToSee : wantToSeeList) {
                Movie movie = movieRepository.findByMovieKey(wantToSee.getMovieKey());
                movieMap.put("MovieKey", movie.getMovieKey());
                movieMap.put("MovieName", movie.getName());
            }
            model.addAttribute("WantToSeeList", movieMap);
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
        List<BigDecimal> resultList = query.getResultList();
        if (resultList.size() > 0) {
            BigDecimal result = resultList.get(0);
            model.addAttribute("followerRank", result.intValue());
        } else {
            model.addAttribute("followerRank", "n/s");
        }
        return "user.html";
    }
}