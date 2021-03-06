package com.redwood.rottenpotato.security.service;

import com.redwood.rottenpotato.email.Mail;
import com.redwood.rottenpotato.email.service.EmailService;
import com.redwood.rottenpotato.main.enums.AjaxCallStatus;
import com.redwood.rottenpotato.main.repositories.*;
import com.redwood.rottenpotato.main.services.JsonService;
import com.redwood.rottenpotato.security.model.User;
import com.redwood.rottenpotato.security.repository.UserRepository;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JsonService jsonService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private EmailService emailService;
    @Autowired
    private WantToSeeRepository wantToSeeRepository;
    @Autowired
    private NotInterestedRepository notInterestedRepository;
    @Autowired
    private UserRatingRepository userRatingRepository;
    @Autowired
    private UserReviewRepository userReviewRepository;
    @Autowired
    private CriticApplicationRepository criticApplicationRepository;
    @Autowired
    private FCountRepository fCountRepository;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public String changeEmail(String userEmail, String currPassword, String newEmail) {
        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "Can't find user");
        }
        if (!passwordEncoder.matches(currPassword, user.getPassword())) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "wrong password");
        }
        if (!EmailValidator.getInstance().isValid(newEmail)) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "invalid email address");
        }
        user.setEmail(newEmail);
        userRepository.save(user);
        return jsonService.constructStatusMessage(AjaxCallStatus.OK);
    }

    public String changePassword(String userEmail, String currPassword, String newPassword) {
        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "Can't find user");
        }
        if (!passwordEncoder.matches(currPassword, user.getPassword())) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "wrong password");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return jsonService.constructStatusMessage(AjaxCallStatus.OK);
    }

    public String editNames(String userEmail, String newFirstName, String newLastName) {
        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "Can't find user");
        }
        user.setFirstName(newFirstName);
        user.setLastName(newLastName);
        userRepository.save(user);
        return jsonService.constructStatusMessage(AjaxCallStatus.OK);
    }


    public String forgotPassword(String email, String appUrl) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "Can't find user");
        }
        String token = UUID.randomUUID().toString().replace("-", "");
        user.setToken(token);
        user.setTokenEndTime(new Timestamp(System.currentTimeMillis() + 1200000));
        userRepository.save(user);
        Mail mail = new Mail();
        String subject = "Restore Password";
        String confirmationUrl = appUrl + "/restorePassword?token=" + token;
        mail.setTo(email);
        mail.setSubject(subject);
        mail.setContent("Restore Password by clicking the link below in the next 20 minutes\n" + confirmationUrl);
        emailService.sendSimpleMessage(mail);
        return jsonService.constructStatusMessage(AjaxCallStatus.OK);
    }

    public String restorePassword(String email, String token, String newPassword) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "Can't find user");
        }
        String userToken = user.getToken();
        Timestamp userTokenTime = user.getTokenEndTime();
        long timeDiff = userTokenTime.getTime() - System.currentTimeMillis();
        if (userToken == null || !userToken.equals(token) || timeDiff < 0) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "token invalid or expired");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return jsonService.constructStatusMessage(AjaxCallStatus.OK);
    }

    public String deleteAccount(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "Can't find user");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return jsonService.constructStatusMessage(AjaxCallStatus.ERROR, "wrong password");
        }
        long userId = user.getId();
        criticApplicationRepository.removeByUserId(userId);
        userRatingRepository.removeByUserId(userId);
        userReviewRepository.removeByUserId(userId);
        userRepository.removeById(userId);
        return jsonService.constructStatusMessage(AjaxCallStatus.OK);
    }

    public void sendVerifyEmail(String email, String appUrl) {
        User user = userRepository.findByEmail(email);

        String token = UUID.randomUUID().toString().replace("-", "");
        user.setToken(token);
        user.setTokenEndTime(new Timestamp(System.currentTimeMillis() + 1200000));
        userRepository.save(user);
        Mail mail = new Mail();
        String subject = "Verify";
        String confirmationUrl = appUrl + "/verify?token=" + token;
        mail.setTo(email);
        mail.setSubject(subject);
        mail.setContent("Verify Account by clicking the link below in the next 20 minutes\n" + confirmationUrl);
        emailService.sendSimpleMessage(mail);
    }
}
