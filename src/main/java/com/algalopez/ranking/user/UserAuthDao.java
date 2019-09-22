package com.algalopez.ranking.user;

import com.algalopez.ranking.user.data.UserAuth;

public interface UserAuthDao {

    Long createUserAuth(String username, String password, String role);

    void updateUserAuth(UserAuth auth);
}
