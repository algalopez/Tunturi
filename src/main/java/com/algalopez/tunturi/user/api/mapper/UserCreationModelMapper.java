package com.algalopez.tunturi.user.api.mapper;

import com.algalopez.tunturi.common.Mapper;
import com.algalopez.tunturi.user.api.UserCreationRequest;
import com.algalopez.tunturi.user.model.User;
import com.algalopez.tunturi.user.model.UserAuth;
import com.algalopez.tunturi.user.model.UserInfo;
import com.algalopez.tunturi.user.model.UserRole;

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
