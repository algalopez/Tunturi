package com.algalopez.tunturi.user.data;

import com.algalopez.tunturi.user.data.model.UserAuth;

public interface UserAuthDao {

    UserAuth findUserAuthById(Long id);

    Long createUserAuth(UserAuth auth);

    void updateUserAuth(UserAuth auth);

    void patchUserAuthPassword(Long id, String encodedPassword);
}
