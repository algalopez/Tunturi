package com.algalopez.tunturi.user.data.adapter;

import com.algalopez.tunturi.user.data.UserInfoDao;
import com.algalopez.tunturi.user.data.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserInfoDaoAdapter implements com.algalopez.tunturi.user.core.UserInfoDao {

    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public com.algalopez.tunturi.user.core.model.UserInfo findUserInfoById(Long id) {

        UserInfo userInfo = userInfoDao.findUserInfoById(id);
        return new UserInfoMapper().mapTo(userInfo);
    }

    @Override
    public Long createUserInfo(com.algalopez.tunturi.user.core.model.UserInfo modelUserInfo) {

        UserInfo dataUserInfo = new UserInfoMapper().mapFrom(modelUserInfo);
        return userInfoDao.createUserInfo(dataUserInfo);
    }

    @Override
    public void updateUserInfo(com.algalopez.tunturi.user.core.model.UserInfo modelUserInfo) {

        UserInfo dataUserInfo = new UserInfoMapper().mapFrom(modelUserInfo);
        userInfoDao.updateUserInfo(dataUserInfo);
    }
}
