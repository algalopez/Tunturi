package com.algalopez.tunturi.user.data;

import com.algalopez.tunturi.user.data.model.UserInfo;

public interface UserInfoDao {

    UserInfo findUserInfoById(Long id);

    Long createUserInfo(UserInfo userInfo);

    void updateUserInfo(UserInfo userInfo);
}
