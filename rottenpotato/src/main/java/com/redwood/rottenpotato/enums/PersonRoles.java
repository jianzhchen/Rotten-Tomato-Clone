package com.redwood.rottenpotato.enums;

public enum PersonRoles {
    ACTOR("ROLE_ACTOR"),
    DIRECTOR("ROLE_DIRECTOR"),
    WRITER("ROLE_WRITER");

    private String str;

    PersonRoles(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
