package com.redwood.rottenpotato.main.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CriticApplication {

    @Id
    private Long userId;
    private String content;

    public CriticApplication() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
