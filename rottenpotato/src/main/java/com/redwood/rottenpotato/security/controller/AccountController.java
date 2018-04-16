package com.redwood.rottenpotato.security.controller;

import com.google.gson.JsonObject;
import com.redwood.rottenpotato.enums.AjaxCallStatus;
import com.redwood.rottenpotato.restful.SignupForm;
import com.redwood.rottenpotato.security.exception.EmailExistsException;
import com.redwood.rottenpotato.security.properties.AuthErrorProperty;
import com.redwood.rottenpotato.security.service.AccountService;
import com.redwood.rottenpotato.services.JsonService;
import com.redwood.rottenpotato.security.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@EnableAutoConfiguration
@RequestMapping("/api0/auth")
public class AccountController {

    @Autowired
    private JsonService jsonService;
    @Autowired
    private ValidationService validationService;
    @Autowired
    private AuthErrorProperty authErrorProperty;
    @Autowired
    private AccountService accountService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ResponseBody
    public String accountRegistration(@RequestBody SignupForm signupForm) {
        if (!validationService.validEmail(signupForm.getEmail())) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, authErrorProperty.getErrorMessage(0));
        }
        if (!validationService.validPassword(signupForm.getPassword(), signupForm.getPasswordConfirm())) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, authErrorProperty.getErrorMessage(1));
        }
        try {
            accountService.createAccount(signupForm);
        } catch (EmailExistsException e) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, authErrorProperty.getErrorMessage(2));
        }
        return jsonService.constructStatusMessage(AjaxCallStatus.OK);
    }
}