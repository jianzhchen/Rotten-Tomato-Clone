package com.redwood.rottenpotato.main.restcontrollers;

import com.redwood.rottenpotato.main.enums.AjaxCallStatus;
import com.redwood.rottenpotato.main.models.Movie;
import com.redwood.rottenpotato.main.models.TV;
import com.redwood.rottenpotato.main.repositories.CriticApplicationRepository;
import com.redwood.rottenpotato.main.repositories.MovieRepository;
import com.redwood.rottenpotato.main.repositories.TVRepository;
import com.redwood.rottenpotato.main.services.JsonService;
import com.redwood.rottenpotato.main.services.MovieService;
import com.redwood.rottenpotato.main.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/1/admin")
public class AdminRestController {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TVRepository tVRepository;
    @Autowired
    private MovieService movieService;
    @Autowired
    private JsonService jsonService;
    @Autowired
    private UserService userService;
    @Autowired
    private CriticApplicationRepository criticApplicationRepository;


    @Transactional
    @PostMapping("delete")
    public String delete(@RequestParam("key") String key) {
        movieRepository.removeByMovieKey(key);
        tVRepository.removeByTVKey(key);
        return jsonService.constructStatusMessage(AjaxCallStatus.OK);
    }

    @Transactional
    @PostMapping("rejectCriticApp")
    public String rejectCriticApp(@RequestParam("userId") long userId) {
        criticApplicationRepository.removeByUserId(userId);
        return jsonService.constructStatusMessage(AjaxCallStatus.OK);
    }

    @PostMapping("reportUser")
    public String reportUser(@RequestParam("userId") long userId, Principal principal, @RequestParam("content") String content) {
        String userEmail = principal.getName();
        return userService.reportUser(userId,userEmail,content);
    }

    @PostMapping("deleteUser")
    public String deleteUser(@RequestParam("userId") long userId, Principal principal){


    }

}
