package com.algalopez.ranking.user.data;

public interface UserAuthDao {

    Long createUserAuth(String username, String password, String role);

    void updateUserAuth(UserAuth auth);
}
