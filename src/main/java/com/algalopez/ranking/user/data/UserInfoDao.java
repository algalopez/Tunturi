package com.algalopez.ranking.user.data;

public interface UserInfoDao {

    UserInfo findUserInfoById(Long id);

    Long createUserInfo(Long id, String email);

    void updateUserInfo(UserInfo userInfo);
}
