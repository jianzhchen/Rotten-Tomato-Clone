package com.redwood.rottenpotato.services;

import com.redwood.rottenpotato.enums.AjaxCallStatus;
import com.redwood.rottenpotato.enums.MovieStatus;
import org.springframework.stereotype.Service;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;

import javax.annotation.PostConstruct;

@Service
public class JsonService {

    private static JsonParser jsonParser;

    @PostConstruct
    public void initialize() {
        jsonParser = new JsonParser();
    }

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

    //Method for constructing movie status message
    public String constructMovieStatusMessage(MovieStatus status)
    {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("status", status.toString());
        return jsonObject.toString();
    }
}
