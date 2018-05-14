package com.redwood.rottenpotato.main.services;

import com.redwood.rottenpotato.main.enums.AjaxCallStatus;
import com.redwood.rottenpotato.main.models.UserReport;
import com.redwood.rottenpotato.main.models.UserReview;
import com.redwood.rottenpotato.main.models.UserReviewReport;
import com.redwood.rottenpotato.main.repositories.UserReportRepository;
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
    private UserReportRepository userReportRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JsonService jsonService;


    public String reportUser(long userId, String userEmail, String content)
    {
        //From user
        User fromUser = userRepository.findByEmail(userEmail);
        if (fromUser == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "Can't find user");
        }

        //To user
        User toUser = userRepository.findById(userId);
        if (toUser == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "user you want to report doesn't exist");
        }

        UserReport userReport = new UserReport();
        userReport.setReportFromId(fromUser.getId());
        userReport.setReportToId(toUser.getId());
        userReport.setContent(content);
        userReportRepository.save(userReport);
        return jsonService.constructStatusMessage(AjaxCallStatus.OK);
    }


}
