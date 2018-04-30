package com.redwood.rottenpotato.security.restcontrollers;

import com.redwood.rottenpotato.main.enums.AjaxCallStatus;
import com.redwood.rottenpotato.main.services.JsonService;
import com.redwood.rottenpotato.security.model.User;
import com.redwood.rottenpotato.security.repository.UserRepository;
import com.redwood.rottenpotato.security.service.AccountService;
import com.redwood.rottenpotato.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

@RestController
public class VerifyRestController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JsonService jsonService;
    @Autowired
    private AccountService accountService;


    @RequestMapping("/verifyResend")
    public String verifyAccountResend(@RequestParam("email") String email, HttpServletRequest request) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "Can't find email");
        }
        String path = String.format("%s://%s:%d", request.getScheme(), request.getServerName(), request.getServerPort());
        accountService.sendVerifyEmail(email, path);
        return jsonService.constructStatusMessage(AjaxCallStatus.OK);
    }
}
