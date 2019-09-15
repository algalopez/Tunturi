package com.algalopez.ranking.user;

import java.util.Arrays;

public enum UserLevel {

    UNDEFINED(0),
    BEGINNER(1),
    EXPERT(8);

    private Integer levelValue;

    UserLevel(Integer levelValue) {
        this.levelValue = levelValue;
    }

    public static UserLevel parseUserLevel(Integer levelValue) {

        if (levelValue == null) {
            return null;
        }

        return Arrays.stream(UserLevel.values())
                .filter(e -> e.levelValue.equals(levelValue))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public Integer getLevelValue() {
        return this.levelValue;
    }
}
