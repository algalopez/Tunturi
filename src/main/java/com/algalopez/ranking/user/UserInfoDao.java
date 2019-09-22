package com.algalopez.ranking.user;

import com.algalopez.ranking.user.data.UserInfo;

public interface UserInfoDao {

    UserInfo findUserById(Long id);

    Long createUser(Long id, String email);

    void updateUser(UserInfo userInfo);
}
