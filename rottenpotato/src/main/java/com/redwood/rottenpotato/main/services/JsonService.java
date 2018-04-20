package com.redwood.rottenpotato.main.services;

import com.redwood.rottenpotato.main.enums.AjaxCallStatus;
import org.springframework.stereotype.Service;
import com.google.gson.JsonObject;


@Service
public class JsonService {
    public String constructStatusMessage(AjaxCallStatus status, String message) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("status", status.toString());
        jsonObject.addProperty("message", message);
        return jsonObject.toString();
    }

    public String constructStatusMessage(AjaxCallStatus status) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("status", status.toString());
        return jsonObject.toString();
    }
}
