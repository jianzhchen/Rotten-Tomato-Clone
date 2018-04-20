package com.redwood.rottenpotato.security.restcontrollers;

import com.redwood.rottenpotato.security.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
public class AccountRestoreRestController {
    @Autowired
    private AccountService accountService;

    @PostMapping("forgotPassword")
    public String forgotPassword(@RequestParam("email") String email, HttpServletRequest request) {
        String path = String.format("%s://%s:%d", request.getScheme(), request.getServerName(), request.getServerPort());
        return accountService.forgotPassword(email, path);
    }

    @PostMapping("restorePassword")
    public String restorePassword(@RequestParam("email") String email, @RequestParam("token") String token, @RequestParam("newPassword") String newPassword) {
        return accountService.restorePassword(email, token, newPassword);
    }

}
