package com.redwood.rottenpotato.controllers;

import com.google.gson.JsonObject;
import com.redwood.rottenpotato.enums.AjaxCallStatus;
import com.redwood.rottenpotato.services.JsonService;
import com.redwood.rottenpotato.security.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
//@RequestMapping("/auth")
public class HomeController
{
    @Autowired
    private JsonService jsonService;

    @Autowired
    private ValidationService validationService;

    //@RequestMapping(value = "/signup", method = RequestMethod.POST, consumes = "text/plain")
    public String accountRegistration(@RequestBody String postPayload)
    {
        JsonObject userForm = jsonService.parseAsJsonObject(postPayload);
        boolean emailValid = validationService.validEmail(userForm.get("email").getAsString());
        boolean passwordValid =validationService.validPassword(userForm.get("password").getAsString(),
                userForm.get("passwordConfirm").getAsString());
        if(!emailValid || !passwordValid)
        {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR,"");
        }
        return "";
    }
}
