package com.algalopez.tunturi.user;

import com.algalopez.tunturi.user.data.UserAuthDao;
import com.algalopez.tunturi.user.data.UserInfoDao;
import com.algalopez.tunturi.user.model.User;
import com.algalopez.tunturi.user.model.mapper.UserDataMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserAuthDao userAuthDao;
    private UserInfoDao userInfoDao;

    public UserServiceImpl(UserAuthDao userAuthDao, UserInfoDao userInfoDao) {
        this.userAuthDao = userAuthDao;
        this.userInfoDao = userInfoDao;
    }

    @Override
    public User findUserById(Long id) {

        com.algalopez.tunturi.user.data.UserAuth userAuth = userAuthDao.findUserAuthById(id);
        com.algalopez.tunturi.user.data.UserInfo userInfo = userInfoDao.findUserInfoById(id);

        return new UserDataMapper().mapFrom(new UserDataMapper.DataUser(userAuth, userInfo));
    }

    @Override
    public Long createUser(User user) {

        UserDataMapper.DataUser dataUser = new UserDataMapper().mapTo(user);
        Long userId = userAuthDao.createUserAuth(dataUser.getUserAuth());

        com.algalopez.tunturi.user.data.UserInfo dataUserInfo = dataUser.getUserInfo();
        dataUserInfo.setId(userId);
        userInfoDao.createUserInfo(dataUserInfo);
        return userId;
    }

    @Override
    public void updateUser(User user) {

        UserDataMapper.DataUser dataUser = new UserDataMapper().mapTo(user);

        userAuthDao.updateUserAuth(dataUser.getUserAuth());
        userInfoDao.updateUserInfo(dataUser.getUserInfo());
    }

    @Override
    public void updateUserInfo(User user) {

        String oldUsername = userInfoDao.findUserInfoById(user.getUserInfo().getId()).getUsername();
        user.getUserInfo().setUsername(oldUsername);
        userInfoDao.updateUserInfo(new UserDataMapper().mapTo(user).getUserInfo());
    }

    @Override
    public void patchUserPassword(Long id, String encodedPassword) {
        userAuthDao.patchUserAuthPassword(id, encodedPassword);
    }
}
