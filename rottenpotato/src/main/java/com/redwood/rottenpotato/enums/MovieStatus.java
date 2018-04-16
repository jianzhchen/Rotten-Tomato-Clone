package com.redwood.rottenpotato.enums;

public enum MovieStatus
{
    SUCCESS("SUCCESS"),
    FAILURE("FAILURE");

    private final String text;

    MovieStatus(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
