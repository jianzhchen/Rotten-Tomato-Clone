package com.redwood.rottenpotato.models;

import javax.persistence.Entity;

@Entity
public class Member extends Account {
    private String username;
    private boolean isCritic;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isCritic() {
        return isCritic;
    }

    public void setCritic(boolean critic) {
        isCritic = critic;
    }
}
