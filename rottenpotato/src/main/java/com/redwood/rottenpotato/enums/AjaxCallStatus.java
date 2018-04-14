package com.redwood.rottenpotato.enums;

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
