package com.redwood.rottenpotato.security.service;

import com.google.gson.JsonObject;
import com.redwood.rottenpotato.email.Mail;
import com.redwood.rottenpotato.email.service.EmailService;
import com.redwood.rottenpotato.restful.SignupForm;
import com.redwood.rottenpotato.security.exception.EmailExistsException;
import com.redwood.rottenpotato.security.model.Account;
import com.redwood.rottenpotato.admin.models.CriticAppReview;
import com.redwood.rottenpotato.security.model.Member;
import com.redwood.rottenpotato.security.model.VerificationToken;
import com.redwood.rottenpotato.security.repository.AccountRepository;
import com.redwood.rottenpotato.admin.repositories.CriticAppReviewRepository;
import com.redwood.rottenpotato.security.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountRepository memberRepository;
    @Autowired
    private CriticAppReviewRepository criticAppReviewRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    @Autowired
    private EmailService emailService;

    @Transactional
    public Account createAccount(SignupForm signupForm) throws EmailExistsException {
        if (emailExist(signupForm.getEmail())) {
            throw new EmailExistsException();
        }
        Member member = new Member();
        member.setPassword(bCryptPasswordEncoder.encode(signupForm.getPassword()));
        member.setEmail(signupForm.getEmail());
        member.setUsername(signupForm.getUsername());
        member.setCritic(false);
        if (signupForm.isCritic()) {
            criticAppReviewRepository.save(new CriticAppReview(member));
        }
        return memberRepository.save(member);
    }

    private boolean emailExist(String email) {
        Account account = accountRepository.findByEmail(email);
        return account != null;
    }

    public void accountVerification(Account account, String appUrl) {
        String token = UUID.randomUUID().toString();
        createVerificationToken(account, token);
        Mail mail = new Mail();
        String recipientAddress = account.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl = appUrl + "/api0/verification.html?token=" + token;
        mail.setTo(recipientAddress);
        mail.setSubject(subject);
        mail.setContent("Confirm your email by clicking the link below\n" + confirmationUrl);
        emailService.sendSimpleMessage(mail);
    }

    private void createVerificationToken(Account account, String token) {
        VerificationToken myToken = new VerificationToken(token, account);
        verificationTokenRepository.save(myToken);
    }

    public boolean verifyToken(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        if (verificationToken == null) {
            return false;
        }
        Account account = verificationToken.getAccount();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            return false;
        }
        account.setEnabled(true);
        accountRepository.save(account);
        return true;
    }


}
