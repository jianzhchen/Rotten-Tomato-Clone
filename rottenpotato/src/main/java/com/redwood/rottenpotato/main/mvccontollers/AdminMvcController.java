package com.redwood.rottenpotato.main.mvccontollers;

import com.redwood.rottenpotato.main.models.*;
import com.redwood.rottenpotato.main.repositories.*;
import com.redwood.rottenpotato.main.services.PrincipleService;
import com.redwood.rottenpotato.security.model.User;
import com.redwood.rottenpotato.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/1/admin")
public class AdminMvcController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserReviewReportRepository userReviewReportRepository;
    @Autowired
    private UserReviewRepository userReviewRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TVRepository tvRepository;
    @Autowired
    private CriticApplicationRepository criticApplicationRepository;
    @Autowired
    private PrincipleService principleService;
    @RequestMapping("reports")
    public String adminReports(Principal principal, Model model) {
        User user = userRepository.findByEmail(principal.getName());
        if (!user.isAdmin()) {
            return "error.html";
        }
        List<UserReviewReport> userReviewReports = userReviewReportRepository.findTop10ByOrderById(PageRequest.of(0, 20));
        List<HashMap> list = new ArrayList<>();
        for (UserReviewReport userReviewReport : userReviewReports) {
            HashMap<String, String> map = new HashMap<>();
            UserReview userReview = userReviewRepository.findById(userReviewReport.getReviewId());
            map.put("review", userReview.getContent());
            map.put("reviewId", userReview.getId() + "");
            map.put("reason", userReviewReport.getContent());
            map.put("reportId", userReviewReport.getId() + "");

            String itemKey = userReview.getItemKey();
            Movie movie = movieRepository.findByMovieKey(itemKey);
            TV tv = tvRepository.findByTVKey(itemKey);
            if (movie != null) {
                map.put("url", "/m/" + movie.getMovieKey());
                map.put("key", movie.getMovieKey());
                map.put("name", movie.getName());

            } else {
                map.put("url", "/t/" + tv.getTVKey());
                map.put("key", tv.getTVKey());
                map.put("name", tv.getTVName());
            }
            list.add(map);

        }
        model.addAttribute("reports", list);
        return "adminReports.html";
    }

    @RequestMapping("ListCriticApp")
    public String listCriticApp(Principal principal, Model model) {
        User user = userRepository.findByEmail(principal.getName());
        if (!user.isAdmin()) {
            return "error.html";
        }
        List<CriticApplication> criticApplications = criticApplicationRepository.findAllBy();

        List<HashMap> list = new ArrayList<>();
        for (CriticApplication criticApplication : criticApplications) {
            HashMap<String, String> map = new HashMap<>();
            long userId = criticApplication.getUserId();
            User userEntity = userRepository.findById(userId);
            map.put("name", userEntity.getFirstName() + " " + userEntity.getLastName());
            map.put("userId", Long.toString(userEntity.getId()));
            map.put("reason", criticApplication.getContent());
            list.add(map);
        }
        model.addAttribute("list", list);
        return "adminCriticApplication.html";
    }

}
