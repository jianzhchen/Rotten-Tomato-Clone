package com.redwood.rottenpotato.main.restcontrollers;

import com.redwood.rottenpotato.main.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/1")
public class ReviewRestController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("postReview")
    public String postReview(@RequestParam("movieKey") String movieKey, Principal principal, @RequestParam("content") String content) {
        String userEmail = principal.getName();
        return reviewService.postReview(movieKey,userEmail,content);
    }

    @PostMapping("reportReview")
    public String reportReview(@RequestParam("reviewId") long reviewId, Principal principal, @RequestParam("content") String content) {
        String userEmail = principal.getName();
        return reviewService.reportReview(reviewId,userEmail,content);
    }

    @PostMapping("deleteReview")
    public String deleteReview(@RequestParam("reviewId") long reviewId, Principal principal) {
        String userEmail = principal.getName();
        return reviewService.deleteReview(reviewId,userEmail);
    }

    @PostMapping("editReview")
    public String editReview(@RequestParam("reviewId") long reviewId, Principal principal, @RequestParam("content") String content) {
        String userEmail = principal.getName();
        return reviewService.editReview(reviewId,userEmail,content);
    }
}
