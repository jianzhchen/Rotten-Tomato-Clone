package com.redwood.rottenpotato.main.services;


import com.redwood.rottenpotato.main.models.Movie;
import com.redwood.rottenpotato.main.repositories.CriticReviewRepository;
import com.redwood.rottenpotato.main.repositories.MovieRepository;
import com.redwood.rottenpotato.security.model.User;
import com.redwood.rottenpotato.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PrincipleService {
    @Autowired
    private UserRepository userRepository;

    public void principalModel(Model model, Principal principal){
        if (principal == null) {
            model.addAttribute("isLogin", false);
        } else {
            model.addAttribute("isLogin", true);
            User user = userRepository.findByEmail(principal.getName());
            model.addAttribute("isAdmin",user.isAdmin());
            model.addAttribute("username", user.getFirstName());
        }
    }

}
