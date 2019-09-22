package com.algalopez.ranking.user;

import com.algalopez.ranking.user.data.UserAuthDao;
import com.algalopez.ranking.user.data.UserInfoDao;
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
    public Long createUser(String email, String encodedPassword, String role) {

        Long userId = userAuthDao.createUserAuth(email, encodedPassword, role);
        userInfoDao.createUserInfo(userId, email);
        return userId;
    }
}
