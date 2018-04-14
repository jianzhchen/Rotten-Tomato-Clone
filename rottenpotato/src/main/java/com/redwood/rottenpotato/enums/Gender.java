package com.redwood.rottenpotato.enums;

public enum Gender {
    MALE("GENDER_MALE"),
    FEMALE("GENDER_FEMALE"),;

    private String str;

    Gender(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
