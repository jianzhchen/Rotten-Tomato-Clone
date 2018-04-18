package com.redwood.rottenpotato.admin.models;

import com.redwood.rottenpotato.security.model.Account;

import javax.persistence.*;

@Entity
public class CriticAppReview {
    private Long id;
    private Account account;

    public CriticAppReview(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
