package com.redwood.rottenpotato.security.service;

import com.redwood.rottenpotato.main.enums.AjaxCallStatus;
import com.redwood.rottenpotato.main.models.UserReport;
import com.redwood.rottenpotato.main.repositories.UserReportRepository;
import com.redwood.rottenpotato.main.services.JsonService;
import com.redwood.rottenpotato.security.model.User;
import com.redwood.rottenpotato.security.repository.UserRepository;
import com.redwood.rottenpotato.security.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JsonService jsonService;

    @Autowired
    private UserReportRepository userReportRepository;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User save(UserRegistrationDto registration) {
        User user = new User();
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setEmail(registration.getEmail());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        user.setEnable(false);
        user.setAdmin(false);
        return userRepository.saveAndFlush(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), user.isEnable(), true, true, true
                , mapRolesToAuthorities());
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities() {
        Collection<SimpleGrantedAuthority> collection = new ArrayList<>();
        collection.add(new SimpleGrantedAuthority("ROLE_USER"));
        return collection;
    }

    public String reportUser(long userId, String userEmail, String content)
    {
        //From user
        User fromUser = userRepository.findByEmail(userEmail);
        if (fromUser == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "Can't find user");
        }

        //To user
        User toUser = userRepository.findById(userId);
        if (toUser == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "user you want to report doesn't exist");
        }

        UserReport userReport = new UserReport();
        userReport.setReportFromId(fromUser.getId());
        userReport.setReportToId(toUser.getId());
        userReport.setContent(content);
        userReportRepository.save(userReport);
        return jsonService.constructStatusMessage(AjaxCallStatus.OK);
    }
}
