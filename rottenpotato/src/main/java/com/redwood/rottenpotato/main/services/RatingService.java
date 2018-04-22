package com.redwood.rottenpotato.main.services;

import com.redwood.rottenpotato.main.enums.AjaxCallStatus;
import com.redwood.rottenpotato.main.models.Movie;
import com.redwood.rottenpotato.main.models.UserRating;
import com.redwood.rottenpotato.main.repositories.MovieRepository;
import com.redwood.rottenpotato.main.repositories.RatingRepository;
import com.redwood.rottenpotato.security.model.User;
import com.redwood.rottenpotato.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JsonService jsonService;

    public String postRating(String movieKey, String userEmail, int rating) {
        Movie movie = movieRepository.findByMovieKey(movieKey);
        if (movie == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "movie doesn't exist");
        }
        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "Can't find user");
        }
        if (rating > 5 || rating < 1) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "UserRating out of range");
        }
        UserRating userRatingEntity = new UserRating();
        userRatingEntity.setMovieKey(movie.getMovieKey());
        userRatingEntity.setRating(rating);
        userRatingEntity.setUserId(user.getId());
        ratingRepository.save(userRatingEntity);
        return jsonService.constructStatusMessage(AjaxCallStatus.OK);
    }

    public String editRating(String movieKey, String userEmail, int rating) {
        Movie movie = movieRepository.findByMovieKey(movieKey);
        if (movie == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "movie doesn't exist");
        }
        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "Can't find user");
        }
        if (rating > 5 || rating < 1) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "UserRating out of range");
        }
        UserRating userRatingEntity = ratingRepository.findByMovieKeyAndUserId(movie.getMovieKey(), user.getId());
        if (userRatingEntity == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "rating not found");
        }
        userRatingEntity.setRating(rating);
        ratingRepository.save(userRatingEntity);
        return jsonService.constructStatusMessage(AjaxCallStatus.OK);
    }

    public String deleteRating(String movieKey, String userEmail) {
        Movie movie = movieRepository.findByMovieKey(movieKey);
        if (movie == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "movie doesn't exist");
        }
        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "Can't find user");
        }
        UserRating userRatingEntity = ratingRepository.findByMovieKeyAndUserId(movie.getMovieKey(), user.getId());
        if (userRatingEntity == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "rating not found");
        }
        ratingRepository.removeById(userRatingEntity.getId());
        return jsonService.constructStatusMessage(AjaxCallStatus.OK);
    }
}
