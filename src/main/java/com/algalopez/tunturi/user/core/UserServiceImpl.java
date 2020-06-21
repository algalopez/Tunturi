package com.algalopez.tunturi.user.core;

import com.algalopez.tunturi.user.core.model.User;
import com.algalopez.tunturi.user.core.model.UserAuth;
import com.algalopez.tunturi.user.core.model.UserInfo;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserAuthDao userAuthDao;
    private final UserInfoDao userInfoDao;

    public UserServiceImpl(UserAuthDao userAuthDao, UserInfoDao userInfoDao) {

        this.userAuthDao = userAuthDao;
        this.userInfoDao = userInfoDao;
    }

    @Override
    public User findUserById(Long id) {

        UserAuth userAuth = userAuthDao.findUserAuthById(id);
        UserInfo userInfo = userInfoDao.findUserInfoById(id);
        return User.builder().userAuth(userAuth).userInfo(userInfo).build();
    }

    @Override
    public Long createUser(User user) {

        Long userId = userAuthDao.createUserAuth(user.getUserAuth());
        UserInfo userInfo = user.getUserInfo();
        userInfo.setId(userId);
        userInfoDao.createUserInfo(userInfo);
        return userId;
    }

    @Override
    public void updateUser(User user) {

        userAuthDao.updateUserAuth(user.getUserAuth());
        userInfoDao.updateUserInfo(user.getUserInfo());
    }

    @Override
    public void updateUserInfo(User user) {

        UserInfo userInfo = user.getUserInfo();
        String oldUsername = userInfoDao.findUserInfoById(userInfo.getId()).getUsername();
        userInfo.setUsername(oldUsername);
        userInfoDao.updateUserInfo(user.getUserInfo());
    }

    @Override
    public void patchUserPassword(Long id, String encodedPassword) {

        userAuthDao.patchUserAuthPassword(id, encodedPassword);
    }
}
