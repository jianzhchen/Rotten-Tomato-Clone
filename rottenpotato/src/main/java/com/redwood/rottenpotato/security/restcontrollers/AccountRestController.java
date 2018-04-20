package com.redwood.rottenpotato.security.restcontrollers;

import com.redwood.rottenpotato.security.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/1")
public class AccountRestController {

    @Autowired
    private AccountService accountService;

    @PostMapping("changeEmail")
    public String changeEmail(@RequestParam("newEmail") String newEmail, Principal principal,
                              @RequestParam("password") String currPassword) {
        String userEmail = principal.getName();
        return accountService.changeEmail(userEmail, currPassword, newEmail);
    }

    @PostMapping("changePassword")
    public String changePassword(@RequestParam("newPassword") String newPassword, Principal principal,
                                 @RequestParam("password") String currPassword) {
        String userEmail = principal.getName();
        return accountService.changePassword(userEmail, currPassword, newPassword);
    }

//    @PostMapping("deleteAccount")
//    public String deleteAccount(Principal principal, @RequestParam("password") String currPassword) {
//        String userEmail = principal.getName();
//        return accountService.deleteAccount(userEmail, currPassword);
//    }

}
