package com.redwood.rottenpotato.enums;

public enum SecurityRoles {
    ADMIN("ROLE_ADMIN"),
    MEMEBR("ROLE_MEMBER"),
    CRITIC("ROLE_CRITIC");

    private String str;

    SecurityRoles(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
