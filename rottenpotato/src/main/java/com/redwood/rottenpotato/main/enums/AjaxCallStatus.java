package com.redwood.rottenpotato.main.enums;

public enum AjaxCallStatus {
    OK("ok"),
    ERROR("error");

    private final String text;

    AjaxCallStatus(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
