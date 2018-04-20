package com.redwood.rottenpotato.security.service;

import com.redwood.rottenpotato.security.model.User;
import com.redwood.rottenpotato.security.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);
}
