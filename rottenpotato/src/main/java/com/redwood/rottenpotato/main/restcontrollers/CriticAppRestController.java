package com.redwood.rottenpotato.main.restcontrollers;

import com.redwood.rottenpotato.main.enums.AjaxCallStatus;
import com.redwood.rottenpotato.main.models.CriticApplication;
import com.redwood.rottenpotato.main.repositories.CriticApplicationRepository;
import com.redwood.rottenpotato.main.services.FollowService;
import com.redwood.rottenpotato.main.services.JsonService;
import com.redwood.rottenpotato.security.model.User;
import com.redwood.rottenpotato.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/1")
public class CriticAppRestController {


    @Autowired
    private CriticApplicationRepository criticApplicationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JsonService jsonService;

    @PostMapping("criticApplication")
    public String follow(Principal principal, @RequestParam("content") String content) {
        CriticApplication criticApplication = new CriticApplication();
        criticApplication.setContent(content);
        String userEmail = principal.getName();
        User user = userRepository.findByEmail(userEmail);
        criticApplication.setUserId(user.getId());
        criticApplicationRepository.save(criticApplication);
        return jsonService.constructStatusMessage(AjaxCallStatus.OK);
    }

}
