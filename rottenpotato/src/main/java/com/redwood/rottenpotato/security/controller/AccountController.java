package com.redwood.rottenpotato.security.controller;

import com.google.gson.JsonObject;
import com.redwood.rottenpotato.enums.AjaxCallStatus;
import com.redwood.rottenpotato.security.exception.EmailExistsException;
import com.redwood.rottenpotato.security.properties.AuthErrorProperty;
import com.redwood.rottenpotato.security.service.AccountService;
import com.redwood.rottenpotato.services.JsonService;
import com.redwood.rottenpotato.security.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
@RequestMapping("/auth")
public class AccountController {

    @Autowired
    private JsonService jsonService;
    @Autowired
    private ValidationService validationService;
    @Autowired
    private AuthErrorProperty authErrorProperty;
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/signup", method = RequestMethod.POST, consumes = "text/plain")
    public String accountRegistration(@RequestBody String postPayload) {
        JsonObject signupForm = jsonService.parseAsJsonObject(postPayload);
        if (!validationService.validEmail(signupForm.get("email").getAsString())) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, authErrorProperty.getErrorMessage(0));
        }
        if (!validationService.validPassword(signupForm.get("password").getAsString(), signupForm.get("passwordConfirm").getAsString())) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, authErrorProperty.getErrorMessage(1));
        }
        try{
            accountService.createAccount(signupForm);
        }catch (EmailExistsException e){
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, authErrorProperty.getErrorMessage(2));
        }
        return jsonService.constructStatusMessage(AjaxCallStatus.OK);
    }
}
