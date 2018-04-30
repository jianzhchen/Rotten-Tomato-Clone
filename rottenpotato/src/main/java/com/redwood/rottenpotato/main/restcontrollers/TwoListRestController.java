package com.redwood.rottenpotato.main.restcontrollers;

import com.redwood.rottenpotato.main.repositories.WantToSeeRepository;
import com.redwood.rottenpotato.main.services.ReviewService;
import com.redwood.rottenpotato.main.services.TwoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/1")
public class TwoListRestController {

    @Autowired
    private TwoListService twoListService;

    @PostMapping("addWantToSee")
    public String addWantToSee(@RequestParam("itemKey") String itemKey, Principal principal) {
        String userEmail = principal.getName();
        return twoListService.addWantToSee(itemKey, userEmail);
    }

    @PostMapping("removeWantToSee")
    public String removeWantToSee(@RequestParam("itemKey") String itemKey, Principal principal) {
        String userEmail = principal.getName();
        return twoListService.removeWantToSee(itemKey, userEmail);
    }

    @PostMapping("addNotInterested")
    public String addNotInterested(@RequestParam("itemKey") String itemKey, Principal principal) {
        String userEmail = principal.getName();
        return twoListService.addNotInterested(itemKey, userEmail);
    }

    @PostMapping("removeNotInterested")
    public String removeNotInterested(@RequestParam("itemKey") String itemKey, Principal principal) {
        String userEmail = principal.getName();
        return twoListService.removeNotInterested(itemKey, userEmail);
    }
}
