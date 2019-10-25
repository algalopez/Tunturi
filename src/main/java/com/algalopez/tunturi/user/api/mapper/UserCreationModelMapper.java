package com.algalopez.tunturi.user.api.mapper;

import com.algalopez.tunturi.common.Mapper;
import com.algalopez.tunturi.user.api.model.UserCreationRequest;
import com.algalopez.tunturi.user.core.model.User;
import com.algalopez.tunturi.user.core.model.UserAuth;
import com.algalopez.tunturi.user.core.model.UserInfo;
import com.algalopez.tunturi.user.core.model.UserRole;

public class UserCreationModelMapper implements Mapper<UserCreationRequest, User> {

    @Override
    public User mapTo(UserCreationRequest userCreationRequest) {

        if (userCreationRequest == null) {
            return null;
        }

        UserAuth modelUserAuth = UserAuth.builder()
                .username(userCreationRequest.getEmail())
                .password(userCreationRequest.getPassword())
                .enabled(true)
                .locked(false)
                .role(UserRole.USER)
                .build();


        UserInfo modelUserInfo = UserInfo.builder()
                .username(userCreationRequest.getEmail())
                .email(userCreationRequest.getEmail())
                .level(null)
                .build();

        return User.builder().userAuth(modelUserAuth).userInfo(modelUserInfo).build();
    }

    @Override
    public UserCreationRequest mapFrom(User o) {

        throw new UnsupportedOperationException();
    }
}
