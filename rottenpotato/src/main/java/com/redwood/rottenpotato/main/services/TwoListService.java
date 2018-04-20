package com.redwood.rottenpotato.main.services;

import com.redwood.rottenpotato.main.enums.AjaxCallStatus;
import com.redwood.rottenpotato.main.models.*;
import com.redwood.rottenpotato.main.repositories.*;
import com.redwood.rottenpotato.security.model.User;
import com.redwood.rottenpotato.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TwoListService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JsonService jsonService;
    @Autowired
    private WantToSeeRepository wantToSeeRepository;
    @Autowired
    private NotInterestedRepository notInterestedRepository;

    public String addWantToSee(String movieKey, String userEmail) {
        Movie movie = movieRepository.findByMovieKey(movieKey);
        if (movie == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "movie doesn't exist");
        }
        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "Can't find user");
        }
        WantToSee wantToSee = new WantToSee();
        wantToSee.setMovieId(movie.getMovieKey());
        wantToSee.setUserId(user.getId());
        wantToSeeRepository.save(wantToSee);
        return jsonService.constructStatusMessage(AjaxCallStatus.OK);
    }

    public String removeWantToSee(String movieKey, String userEmail) {
        Movie movie = movieRepository.findByMovieKey(movieKey);
        if (movie == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "movie doesn't exist");
        }
        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "Can't find user");
        }
        wantToSeeRepository.removeByMovieIdAndUserId(movie.getMovieKey(), user.getId());
        return jsonService.constructStatusMessage(AjaxCallStatus.OK);
    }

    public String addNotInterested(String movieKey, String userEmail) {
        Movie movie = movieRepository.findByMovieKey(movieKey);
        if (movie == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "movie doesn't exist");
        }
        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "Can't find user");
        }
        NotInterested notInterested = new NotInterested();
        notInterested.setMovieId(movie.getMovieKey());
        notInterested.setUserId(user.getId());
        notInterestedRepository.save(notInterested);
        return jsonService.constructStatusMessage(AjaxCallStatus.OK);
    }

    public String removeNotInterested(String movieKey, String userEmail) {
        Movie movie = movieRepository.findByMovieKey(movieKey);
        if (movie == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "movie doesn't exist");
        }
        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "Can't find user");
        }
        notInterestedRepository.removeByMovieIdAndUserId(movie.getMovieKey(), user.getId());
        return jsonService.constructStatusMessage(AjaxCallStatus.OK);
    }

}
