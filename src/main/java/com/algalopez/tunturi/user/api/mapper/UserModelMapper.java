package com.algalopez.tunturi.user.api.mapper;

import com.algalopez.tunturi.common.Mapper;
import com.algalopez.tunturi.user.core.model.User;
import com.algalopez.tunturi.user.core.model.UserAuth;
import com.algalopez.tunturi.user.core.model.UserInfo;
import com.algalopez.tunturi.user.core.model.UserRole;

public class UserModelMapper implements Mapper<com.algalopez.tunturi.user.api.model.User, User> {


    @Override
    public User mapTo(com.algalopez.tunturi.user.api.model.User apiUser) {

        if (apiUser == null) {
            return null;
        }

        UserAuth modelUserAuth = UserAuth.builder()
                .id(apiUser.getId())
                .username(apiUser.getUsername())
                .password(apiUser.getPassword())
                .enabled(apiUser.isEnabled())
                .locked(apiUser.isLocked())
                .role(UserRole.valueOf(apiUser.getRole()))
                .build();

        UserInfo modelUserInfo = UserInfo.builder()
                .id(apiUser.getId())
                .username(apiUser.getUsername())
                .email(apiUser.getEmail())
                .level(apiUser.getLevel())
                .build();

        return User.builder().userAuth(modelUserAuth).userInfo(modelUserInfo).build();
    }

    @Override
    public com.algalopez.tunturi.user.api.model.User mapFrom(User modelUser) {

        if (modelUser == null) {
            return null;
        }

        UserAuth modelUserAuth = modelUser.getUserAuth();
        UserInfo modelUserInfo = modelUser.getUserInfo();

        com.algalopez.tunturi.user.api.model.User.UserBuilder userBuilder = com.algalopez.tunturi.user.api.model.User.builder();

        if (modelUserAuth != null) {
            userBuilder
                    .id(modelUserAuth.getId())
                    .username(modelUserAuth.getUsername())
                    .password(modelUserAuth.getPassword())
                    .enabled(modelUserAuth.isEnabled())
                    .locked(modelUserAuth.isLocked())
                    .role(modelUserAuth.getRole().name());
        }

        if (modelUserInfo != null) {
            userBuilder
                    .email(modelUserInfo.getEmail())
                    .level(modelUserInfo.getLevel());
        }

        return userBuilder.build();
    }
}
