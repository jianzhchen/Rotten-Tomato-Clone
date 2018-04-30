package com.redwood.rottenpotato.security.restcontrollers;

import com.redwood.rottenpotato.security.model.User;
import com.redwood.rottenpotato.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;

@Controller
public class VerifyController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/verify")
    public String verifyAccount(@RequestParam("token") String token, Model model) {
        User user = userRepository.findByToken(token);
        Calendar cal = Calendar.getInstance();
        if ((user.getTokenEndTime().getTime() - cal.getTime().getTime()) <= 0) {
            model.addAttribute("failed", "token expired");
            return "verify.html";
        } else {
            user.setEnable(true);
            userRepository.save(user);
            return "login.html";
        }
    }
}
