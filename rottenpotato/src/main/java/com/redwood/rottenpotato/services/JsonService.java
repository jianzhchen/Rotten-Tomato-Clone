package com.redwood.rottenpotato.services;

import com.redwood.rottenpotato.enums.AjaxCallStatus;
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

    public JsonObject parseAsJsonObject(String jsonText) {
        return jsonParser.parse(jsonText).getAsJsonObject();
    }

    public String constructStatusMessage(AjaxCallStatus status, String message)
    {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("status", status.toString());
        jsonObject.addProperty("message", message);
        return jsonObject.toString();
    }
}
