package com.algalopez.ranking.auth;

public enum UserAuthRole {

    USER("USER"),
    ADMIN("ADMIN");

    private String value;
    UserAuthRole(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
