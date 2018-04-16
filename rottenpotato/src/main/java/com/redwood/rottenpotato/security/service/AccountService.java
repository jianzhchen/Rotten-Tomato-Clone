package com.redwood.rottenpotato.security.service;

import com.google.gson.JsonObject;
import com.redwood.rottenpotato.restful.SignupForm;
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
    public Account createAccount(SignupForm signupForm) throws EmailExistsException {
        if (emailExist(signupForm.getEmail())) {
            throw new EmailExistsException();
        }
        Member member = new Member();
        member.setPassword(bCryptPasswordEncoder.encode(signupForm.getPassword()));
        member.setEmail(signupForm.getEmail());
        member.setUsername(signupForm.getUsername());
        member.setCritic(false);
        if(signupForm.isCritic()){
            criticAppReviewRepository.save(new CriticAppReview(member));
        }
        return memberRepository.saveAndFlush(member);
    }

    private boolean emailExist(String email) {
        Account account = accountRepository.findByEmail(email);
        return account != null;
    }


}
