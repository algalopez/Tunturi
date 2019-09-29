package com.algalopez.ranking.user.data;

public interface UserAuthDao {

    UserAuth findUserAuthById(Long id);

    Long createUserAuth(UserAuth auth);

    void updateUserAuth(UserAuth auth);

    void patchUserAuthPassword(Long id, String encodedPassword);
}
