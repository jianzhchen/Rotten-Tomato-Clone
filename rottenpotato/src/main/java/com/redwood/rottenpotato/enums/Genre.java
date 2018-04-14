package com.redwood.rottenpotato.enums;

public enum Genre {
    Action("Genre_Action"),
    Animation("Genre_Animation"),
    ArtAndForeign("Genre_ArtAndForeign"),
    Classics("Genre_Classics"),
    Comedy("Genre_Comedy"),
    Documentary("Genre_Documentary"),
    Drama("Genre_Drama"),
    Horror("Genre_Horror"),
    KidsAndFamily("Genre_KidsAndFamily"),
    Mystery("Genre_Mystery"),
    Romance("Genre_Romance"),
    SFANDFantasy("Genre_SFANDFantasy");

    private String str;

    Genre(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
