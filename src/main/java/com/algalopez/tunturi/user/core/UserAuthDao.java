package com.algalopez.tunturi.user.core;

import com.algalopez.tunturi.user.core.model.UserAuth;

public interface UserAuthDao {

    UserAuth findUserAuthById(Long id);

    Long createUserAuth(UserAuth auth);

    void updateUserAuth(UserAuth auth);

    void patchUserAuthPassword(Long id, String encodedPassword);
}
