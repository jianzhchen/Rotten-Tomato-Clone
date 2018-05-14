package com.redwood.rottenpotato.main.services;

import com.redwood.rottenpotato.main.enums.AjaxCallStatus;
import com.redwood.rottenpotato.main.models.UserReview;
import com.redwood.rottenpotato.main.models.UserReviewReport;
import com.redwood.rottenpotato.main.repositories.UserReviewReportRepository;
import com.redwood.rottenpotato.main.repositories.UserReviewRepository;
import com.redwood.rottenpotato.security.model.User;
import com.redwood.rottenpotato.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {
    @Autowired
    private UserReviewRepository userReviewRepository;
    @Autowired
    private UserReviewReportRepository userReviewReportRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JsonService jsonService;


    public String reportUser(long userId, String userEmail, String content) {
        UserReview userReview = userReviewRepository.findById(userId);
        if (userReview == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "userReview doesn't exist");
        }
        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "Can't find user");
        }
        UserReviewReport userReviewReport = new UserReviewReport();
        userReviewReport.setUserId(user.getId());
        userReviewReport.setContent(content);
        userReviewReport.setReviewId(userReview.getId());
        userReviewReportRepository.save(userReviewReport);
        return jsonService.constructStatusMessage(AjaxCallStatus.OK);
    }


}
