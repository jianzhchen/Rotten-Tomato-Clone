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

    public String addWantToSee(String itemKey, String userEmail) {
        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "Can't find user");
        }
        WantToSee wantToSee = new WantToSee();
        wantToSee.setItemKey(itemKey);
        wantToSee.setUserId(user.getId());
        wantToSeeRepository.save(wantToSee);
        return jsonService.constructStatusMessage(AjaxCallStatus.OK);
    }

    public String removeWantToSee(String itemKey, String userEmail) {
        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "Can't find user");
        }
        wantToSeeRepository.removeByItemKeyAndUserId(itemKey, user.getId());
        return jsonService.constructStatusMessage(AjaxCallStatus.OK);
    }

    public String addNotInterested(String itemKey, String userEmail) {
        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "Can't find user");
        }
        NotInterested notInterested = new NotInterested();
        notInterested.setItemKey(itemKey);
        notInterested.setUserId(user.getId());
        notInterestedRepository.save(notInterested);
        return jsonService.constructStatusMessage(AjaxCallStatus.OK);
    }

    public String removeNotInterested(String itemKey, String userEmail) {
        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "Can't find user");
        }
        notInterestedRepository.removeByItemKeyAndUserId(itemKey, user.getId());
        return jsonService.constructStatusMessage(AjaxCallStatus.OK);
    }

}
