package com.redwood.rottenpotato.main.mvccontollers;

import com.redwood.rottenpotato.main.models.UserReview;
import com.redwood.rottenpotato.main.models.UserReviewReport;
import com.redwood.rottenpotato.main.repositories.UserReviewReportRepository;
import com.redwood.rottenpotato.main.repositories.UserReviewRepository;
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
            UserReview userReview = userReviewRepository.findById(userReviewReport.getId());
            map.put("review", userReview.getContent());
            map.put("report", userReviewReport.getContent());
        }
        model.addAttribute("reports", list);
        return "adminReports.html";
    }

}
