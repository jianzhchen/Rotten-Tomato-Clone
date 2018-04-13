package com.redwood.rottenpotato.services;

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
}
