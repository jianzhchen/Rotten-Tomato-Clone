package com.redwood.rottenpotato.services;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidationService {

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";
    private Pattern email_pattern;

    @PostConstruct
    public void init() {
        email_pattern = Pattern.compile(EMAIL_PATTERN);
    }

    public boolean validEmail(String email) {
        Matcher matcher = email_pattern.matcher(email);
        return matcher.matches();
    }
}
