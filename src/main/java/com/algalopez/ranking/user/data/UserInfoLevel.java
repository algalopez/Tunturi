package com.algalopez.ranking.user.data;

import java.util.Arrays;

public enum UserInfoLevel {

    UNDEFINED(0),
    BEGINNER(1),
    EXPERT(8);

    private Integer levelValue;

    UserInfoLevel(Integer levelValue) {
        this.levelValue = levelValue;
    }

    public static UserInfoLevel parseUserLevel(Integer levelValue) {

        if (levelValue == null) {
            return null;
        }

        return Arrays.stream(UserInfoLevel.values())
                .filter(e -> e.levelValue.equals(levelValue))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public Integer getLevelValue() {
        return this.levelValue;
    }
}
