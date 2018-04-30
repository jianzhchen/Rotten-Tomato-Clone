package com.redwood.rottenpotato.main.services;

import com.redwood.rottenpotato.main.enums.AjaxCallStatus;
import com.redwood.rottenpotato.main.models.Movie;
import com.redwood.rottenpotato.main.models.UserRating;
import com.redwood.rottenpotato.main.repositories.MovieRepository;
import com.redwood.rottenpotato.main.repositories.UserRatingRepository;
import com.redwood.rottenpotato.security.model.User;
import com.redwood.rottenpotato.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {
    @Autowired
    private UserRatingRepository userRatingRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JsonService jsonService;

    public String postRating(String itemKey, String userEmail, int rating) {
        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "Can't find user");
        }
        if (rating > 5 || rating < 1) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "UserRating out of range");
        }
        UserRating userRatingEntity = new UserRating();
        userRatingEntity.setItemKey(itemKey);
        userRatingEntity.setRating(rating);
        userRatingEntity.setUserId(user.getId());
        userRatingRepository.save(userRatingEntity);
        return jsonService.constructStatusMessage(AjaxCallStatus.OK);
    }

    public String editRating(String itemKey, String userEmail, int rating) {

        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "Can't find user");
        }
        if (rating > 5 || rating < 1) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "UserRating out of range");
        }
        UserRating userRatingEntity = userRatingRepository.findByItemKeyAndUserId(itemKey, user.getId());
        if (userRatingEntity == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "rating not found");
        }
        userRatingEntity.setRating(rating);
        userRatingRepository.save(userRatingEntity);
        return jsonService.constructStatusMessage(AjaxCallStatus.OK);
    }

    public String deleteRating(String itemKey, String userEmail) {
        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "Can't find user");
        }
        UserRating userRatingEntity = userRatingRepository.findByItemKeyAndUserId(itemKey, user.getId());
        if (userRatingEntity == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "rating not found");
        }
        userRatingRepository.removeById(userRatingEntity.getId());
        return jsonService.constructStatusMessage(AjaxCallStatus.OK);
    }
}
