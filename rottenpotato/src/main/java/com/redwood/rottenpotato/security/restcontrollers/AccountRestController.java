package com.redwood.rottenpotato.security.restcontrollers;

import com.redwood.rottenpotato.main.enums.AjaxCallStatus;
import com.redwood.rottenpotato.main.repositories.CriticApplicationRepository;
import com.redwood.rottenpotato.main.repositories.FCountRepository;
import com.redwood.rottenpotato.main.services.JsonService;
import com.redwood.rottenpotato.security.model.User;
import com.redwood.rottenpotato.security.repository.UserRepository;
import com.redwood.rottenpotato.security.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.security.Principal;

@RestController
@RequestMapping("/1")
public class AccountRestController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JsonService jsonService;

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

    @PostMapping("editNames")
    public String editNames(@RequestParam("newFirstName") String newFirstName, Principal principal,
                                 @RequestParam("newLastName") String newLastName) {
        String userEmail = principal.getName();
        return accountService.editNames(userEmail, newFirstName, newLastName);
    }


    @PostMapping("deleteAccount")
    public String deleteAccount(Principal principal, @RequestParam("password") String currPassword) {
        String userEmail = principal.getName();
        return accountService.deleteAccount(userEmail, currPassword);
    }

    @Transactional
    @PostMapping("changePrivacy")
    public String changePrivacy(Principal principal, @RequestParam("openProfile") int openProfile) {
        User user = userRepository.findByEmail(principal.getName());
        if(openProfile==1){
            user.setOpenProfile(true);
        }
        else{
            user.setOpenProfile(false);
        }
        userRepository.save(user);
        return jsonService.constructStatusMessage(AjaxCallStatus.OK);
    }

}
