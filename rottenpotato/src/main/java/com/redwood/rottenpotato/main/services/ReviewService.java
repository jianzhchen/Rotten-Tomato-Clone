package com.redwood.rottenpotato.main.services;

import com.redwood.rottenpotato.main.enums.AjaxCallStatus;
import com.redwood.rottenpotato.main.models.Movie;
import com.redwood.rottenpotato.main.models.TV;
import com.redwood.rottenpotato.main.models.UserReview;
import com.redwood.rottenpotato.main.models.UserReviewReport;
import com.redwood.rottenpotato.main.repositories.MovieRepository;
import com.redwood.rottenpotato.main.repositories.ReviewReportRepository;
import com.redwood.rottenpotato.main.repositories.ReviewRepository;
import com.redwood.rottenpotato.main.repositories.TVRepository;
import com.redwood.rottenpotato.security.model.User;
import com.redwood.rottenpotato.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ReviewReportRepository reviewReportRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JsonService jsonService;
    @Autowired
    private TVRepository tTVRepository;

    public String postReview(String itemKey, String userEmail, String content) {
        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "Can't find user");
        }
        UserReview userReview = new UserReview();
        userReview.setUserId(user.getId());
        userReview.setContent(content);
        userReview.setItemKey(itemKey);
        reviewRepository.save(userReview);
        return jsonService.constructStatusMessage(AjaxCallStatus.OK);
    }

    public String reportReview(long reviewId, String userEmail, String content) {
        UserReview userReview = reviewRepository.findById(reviewId);
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
        reviewReportRepository.save(userReviewReport);
        return jsonService.constructStatusMessage(AjaxCallStatus.OK);
    }

    public String deleteReview(long reviewId, String userEmail) {
        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "Can't find user");
        }

        UserReview userReview = reviewRepository.findById(reviewId);
        if (userReview == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "userReview doesn't exist");
        }
        if (userReview.getUserId() != user.getId()) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "userReview not own by you");
        }
        reviewRepository.removeById(reviewId);
        reviewReportRepository.removeByReviewId(reviewId);
        return jsonService.constructStatusMessage(AjaxCallStatus.OK);
    }

    public String editReview(long reviewId, String userEmail, String content) {
        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "Can't find user");
        }
        UserReview userReview = reviewRepository.findById(reviewId);
        if (userReview == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "userReview doesn't exist");
        }
        if (userReview.getUserId() != user.getId()) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "userReview not own by you");
        }
        userReview.setContent(content);
        reviewRepository.save(userReview);
        return jsonService.constructStatusMessage(AjaxCallStatus.OK);
    }
}
