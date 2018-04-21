package com.redwood.rottenpotato.main.restcontrollers;

import com.redwood.rottenpotato.main.services.FollowService;
import com.redwood.rottenpotato.main.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/1")
public class FollowRestController {

    @Autowired
    private FollowService followService;

    @PostMapping("follow")
    public String follow(Principal principal, @RequestParam("userId") long userId) {
        String userEmail = principal.getName();
        return followService.follow(userEmail, userId);
    }

    @PostMapping("unFollow")
    public String unFollow(Principal principal, @RequestParam("userId") long userId) {
        String userEmail = principal.getName();
        return followService.unFollow(userEmail, userId);
    }
}
