package com.redwood.rottenpotato.security.service;

import com.google.gson.JsonObject;
import com.redwood.rottenpotato.security.exception.EmailExistsException;
import com.redwood.rottenpotato.security.model.Account;
import com.redwood.rottenpotato.admin.models.CriticAppReview;
import com.redwood.rottenpotato.security.model.Member;
import com.redwood.rottenpotato.security.repository.AccountRepository;
import com.redwood.rottenpotato.admin.repositories.CriticAppReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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

    @Transactional
    public Account createAccount(JsonObject signupForm) throws EmailExistsException {
        if (emailExist(signupForm.get("email").getAsString())) {
            throw new EmailExistsException();
        }
        Member member = new Member();
        member.setPassword(bCryptPasswordEncoder.encode(signupForm.get("password").getAsString()));
        member.setEmail(signupForm.get("email").getAsString());
        member.setUsername(signupForm.get("username").getAsString());
        member.setCritic(false);
        if(signupForm.get("isCritic").getAsBoolean()){
            criticAppReviewRepository.save(new CriticAppReview(member));
        }
        return memberRepository.saveAndFlush(member);
    }

    private boolean emailExist(String email) {
        Account account = accountRepository.findByEmail(email);
        return account != null;
    }


}
