package com.redwood.rottenpotato.main.restcontrollers;

import com.redwood.rottenpotato.main.enums.AjaxCallStatus;
import com.redwood.rottenpotato.main.repositories.CriticApplicationRepository;
import com.redwood.rottenpotato.main.repositories.MovieRepository;
import com.redwood.rottenpotato.main.repositories.TVRepository;
import com.redwood.rottenpotato.main.services.JsonService;
import com.redwood.rottenpotato.main.services.MovieService;
import com.redwood.rottenpotato.security.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/1")
public class UserRestController {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TVRepository tVRepository;
    @Autowired
    private MovieService movieService;
    @Autowired
    private JsonService jsonService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private CriticApplicationRepository criticApplicationRepository;

    @PostMapping("/reportUser")
    public String reportUser(@RequestParam("userId") long userId, Principal principal, @RequestParam("content") String content)
    {
        String userEmail = principal.getName();
        return userService.reportUser(userId,userEmail,content);
    }

}
