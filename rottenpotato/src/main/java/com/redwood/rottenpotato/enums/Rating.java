package com.redwood.rottenpotato.enums;

public enum Rating {
    G("RATING_G"),
    PG("RATING_G"),
    PG13("RATING_PG13"),
    R("RATING_R"),
    NC17("RATING_NC17");

    private String str;

    Rating(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
