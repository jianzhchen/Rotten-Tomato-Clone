package com.redwood.rottenpotato.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@PropertySource("classpath:error.properties")
@ConfigurationProperties("error.auth")
public class AuthErrorProperty {

    private final List<String> errorCodes = new ArrayList<>();


    public List<String> getErrorCodes() {
        return errorCodes;
    }

    public String getErrorMessage(int errorCode) {
        return errorCodes.get(errorCode);
    }
}
