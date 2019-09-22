package com.algalopez.ranking.user;

public interface UserService {

    Long createUser(String email, String encodedPassword, String role);
}
