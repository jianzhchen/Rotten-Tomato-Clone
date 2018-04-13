package com.redwood.rottenpotato.controllers;

import com.google.gson.JsonObject;
import com.redwood.rottenpotato.services.JsonService;
import com.redwood.rottenpotato.services.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/auth")
public class AccountController {

    @Autowired
    private JsonService jsonService;

    @Autowired
    private ValidationService validationService;

    @RequestMapping(value = "/signup", method = RequestMethod.POST, consumes = "text/plain")
    public String accountRegistration(@RequestBody String postPayload) {
        JsonObject userForm = jsonService.parseAsJsonObject(postPayload);
        boolean emailValid = validationService.validEmail(userForm.get("email").getAsString());
        return "";
    }
}
