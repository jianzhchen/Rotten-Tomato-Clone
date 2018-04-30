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
    public String postRating(@RequestParam("itemKey") String itemKey, Principal principal, @RequestParam("rating") int rating) {
        String userEmail = principal.getName();
        return ratingService.postRating(itemKey, userEmail, rating);
    }

    @PostMapping("deleteRating")
    public String deleteRating(@RequestParam("itemKey") String itemKey, Principal principal) {
        String userEmail = principal.getName();
        return ratingService.deleteRating(itemKey, userEmail);
    }

    @PostMapping("editRating")
    public String editRating(@RequestParam("itemKey") String itemKey, Principal principal, @RequestParam("rating") int rating) {
        String userEmail = principal.getName();
        return ratingService.editRating(itemKey, userEmail, rating);
    }
}