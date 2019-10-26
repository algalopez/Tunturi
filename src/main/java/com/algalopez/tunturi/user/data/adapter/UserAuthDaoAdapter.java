package com.algalopez.tunturi.user.data.adapter;

import com.algalopez.tunturi.user.data.model.UserAuth;
import com.algalopez.tunturi.user.data.UserAuthDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserAuthDaoAdapter implements com.algalopez.tunturi.user.core.UserAuthDao {

    @Autowired
    private UserAuthDao userAuthDao;

    @Override
    public com.algalopez.tunturi.user.core.model.UserAuth findUserAuthById(Long id) {

        UserAuth dataUserAuth = userAuthDao.findUserAuthById(id);
        return new UserAuthMapper().mapTo(dataUserAuth);
    }

    @Override
    public Long createUserAuth(com.algalopez.tunturi.user.core.model.UserAuth modelUserAuth) {

        UserAuth dataUserAuth = new UserAuthMapper().mapFrom(modelUserAuth);
        return userAuthDao.createUserAuth(dataUserAuth);
    }

    @Override
    public void updateUserAuth(com.algalopez.tunturi.user.core.model.UserAuth modelUserAuth) {

        UserAuth dataUserAuth = new UserAuthMapper().mapFrom(modelUserAuth);
        userAuthDao.updateUserAuth(dataUserAuth);
    }

    @Override
    public void patchUserAuthPassword(Long id, String encodedPassword) {

        userAuthDao.patchUserAuthPassword(id, encodedPassword);
    }
}
