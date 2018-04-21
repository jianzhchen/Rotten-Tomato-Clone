package com.redwood.rottenpotato.main.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FCount {
    @Id
    private long userId;
    private long followerCount;


    public long getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(long followerCount) {
        this.followerCount = followerCount;
    }

    public void incFollowerCount() {
        this.followerCount++;
    }

    public void decFollowerCount() {
        this.followerCount--;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
