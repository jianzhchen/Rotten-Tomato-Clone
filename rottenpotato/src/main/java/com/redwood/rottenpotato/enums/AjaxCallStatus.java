package com.redwood.rottenpotato.enums;

public enum AjaxCallStatus {
    OK("ROLE_ADMIN"),
    ERROR("ROLE_MEMBER");

    private String str;

    AjaxCallStatus(String str) {
        this.str = str;
    }

    public String getString() {
        return str;
    }
}
