package com.redwood.rottenpotato.main.services;

import com.redwood.rottenpotato.main.enums.AjaxCallStatus;
import com.redwood.rottenpotato.main.models.Movie;
import com.redwood.rottenpotato.main.models.Review;
import com.redwood.rottenpotato.main.models.ReviewReport;
import com.redwood.rottenpotato.main.repositories.MovieRepository;
import com.redwood.rottenpotato.main.repositories.ReviewReportRepository;
import com.redwood.rottenpotato.main.repositories.ReviewRepository;
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

    public String postReview(String movieKey, String userEmail, String content) {
        Movie movie = movieRepository.findByMovieKey(movieKey);
        if (movie == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "movie doesn't exist");
        }
        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "Can't find user");
        }
        Review review = new Review();
        review.setUserId(user.getId());
        review.setContent(content);
        review.setMovieKey(movie.getMovieKey());
        reviewRepository.save(review);
        return jsonService.constructStatusMessage(AjaxCallStatus.OK);
    }

    public String reportReview(long reviewId, String userEmail, String content) {
        Review review = reviewRepository.findById(reviewId);
        if (review == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "review doesn't exist");
        }
        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "Can't find user");
        }
        ReviewReport reviewReport = new ReviewReport();
        reviewReport.setUserId(user.getId());
        reviewReport.setContent(content);
        reviewReport.setReviewId(review.getId());
        reviewReportRepository.save(reviewReport);
        return jsonService.constructStatusMessage(AjaxCallStatus.OK);
    }

    public String deleteReview(long reviewId, String userEmail) {
        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "Can't find user");
        }

        Review review = reviewRepository.findById(reviewId);
        if (review == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "review doesn't exist");
        }
        if (review.getUserId() != user.getId()) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "review not own by you");
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
        Review review = reviewRepository.findById(reviewId);
        if (review == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "review doesn't exist");
        }
        if (review.getUserId() != user.getId()) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "review not own by you");
        }
        review.setContent(content);
        reviewRepository.save(review);
        return jsonService.constructStatusMessage(AjaxCallStatus.OK);
    }
}
