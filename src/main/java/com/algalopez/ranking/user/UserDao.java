package com.algalopez.ranking.user;

public interface UserDao {

    User findUserById(Long id);

    Long createUser(Long id, String email);

    void updateUser(User user);
}
