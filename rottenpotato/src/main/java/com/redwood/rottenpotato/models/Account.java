package com.redwood.rottenpotato.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Account {
    private Long id;
    private String email;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}