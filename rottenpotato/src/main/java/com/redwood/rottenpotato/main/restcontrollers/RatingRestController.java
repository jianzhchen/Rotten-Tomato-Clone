package com.redwood.rottenpotato.main.restcontrollers;

import com.redwood.rottenpotato.main.services.RatingService;
import com.redwood.rottenpotato.main.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/1")
public class RatingRestController {

    @Autowired
    private RatingService ratingService;

    @PostMapping("postRating")
    public String postRating(@RequestParam("movieKey") String movieKey, Principal principal, @RequestParam("rating") int rating) {
        String userEmail = principal.getName();
        return ratingService.postRating(movieKey, userEmail, rating);
    }

    @PostMapping("deleteRating")
    public String deleteRating(@RequestParam("movieKey") String movieKey, Principal principal) {
        String userEmail = principal.getName();
        return ratingService.deleteRating(movieKey, userEmail);
    }

    @PostMapping("editRating")
    public String editRating(@RequestParam("movieKey") String movieKey, Principal principal, @RequestParam("rating") int rating) {
        String userEmail = principal.getName();
        return ratingService.editRating(movieKey, userEmail, rating);
    }
}