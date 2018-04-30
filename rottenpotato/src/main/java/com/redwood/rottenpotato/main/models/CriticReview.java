package com.redwood.rottenpotato.main.models;

import javax.persistence.*;
import java.sql.Date;

@Entity // This tells Hibernate to make a table out of this class
public class CriticReview {
    //Primary key for the entity Item
    @Id
    private String reviewKey;
    private String itemKey;
    private String criticKey;
    private String reviewTime;
    private int reviewRating;
    private String reviewContent;
    private Date reviewTimeDate;

    //Constructor for JPA
    public CriticReview() {
    }

    public String getReviewKey() {
        return reviewKey;
    }

    public void setReviewKey(String reviewKey) {
        this.reviewKey = reviewKey;
    }

    public String getItemKey() {
        return itemKey;
    }

    public void setItemKey(String itemKey) {
        this.itemKey = itemKey;
    }

    public String getCriticKey() {
        return criticKey;
    }

    public void setCriticKey(String criticKey) {
        this.criticKey = criticKey;
    }

    public String getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(String reviewTime) {
        this.reviewTime = reviewTime;
    }

    public int getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(int reviewRating) {
        this.reviewRating = reviewRating;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public Date getReviewTimeDate() {
        return reviewTimeDate;
    }

    public void setReviewTimeDate(Date reviewTimeDate) {
        this.reviewTimeDate = reviewTimeDate;
    }
}