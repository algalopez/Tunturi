package com.algalopez.tunturi.user.core;

import com.algalopez.tunturi.user.core.model.UserInfo;

public interface UserInfoDao {

    UserInfo findUserInfoById(Long id);

    Long createUserInfo(UserInfo userInfo);

    void updateUserInfo(UserInfo userInfo);
}
