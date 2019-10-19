package com.algalopez.tunturi.user;

import com.algalopez.tunturi.user.model.User;

public interface UserService {

    User findUserById(Long id);

    Long createUser(User user);

    void updateUserInfo(User user);

    void updateUser(User user);

    void patchUserPassword(Long id, String encodedPassword);
}
