package com.algalopez.ranking.user.model;

import java.util.Arrays;

public enum UserRole {

    USER("USER"),
    ADMIN("ADMIN");

    private String roleValue;

    UserRole(String roleValue) {
        this.roleValue = roleValue;
    }

    public static UserRole parseUserRole(String roleValue) {

        if (roleValue == null) {
            return null;
        }

        return Arrays.stream(UserRole.values())
                .filter(e -> e.roleValue.equals(roleValue))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public String getRoleValue() {
        return this.roleValue;
    }
}
