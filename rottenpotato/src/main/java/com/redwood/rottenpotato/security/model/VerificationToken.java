package com.redwood.rottenpotato.security.model;

import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.Calendar;

@Entity
public class VerificationToken {
    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(targetEntity = Account.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private Account Account;

    private Date expiryDate;

    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

    public VerificationToken() {

    }

    public VerificationToken(String token, Account account) {
        super();
        this.token = token;
        this.Account = account;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    public static int getEXPIRATION() {
        return EXPIRATION;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Account getAccount() {
        return Account;
    }

    public void setAccount(Account account) {
        Account = account;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
