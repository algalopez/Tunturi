package com.algalopez.ranking.user.api.mapper;

import com.algalopez.ranking.common.Mapper;
import com.algalopez.ranking.user.model.User;
import com.algalopez.ranking.user.model.UserAuth;
import com.algalopez.ranking.user.model.UserInfo;
import com.algalopez.ranking.user.model.UserRole;

public class UserModelMapper implements Mapper<com.algalopez.ranking.user.api.User, User> {


    @Override
    public User mapTo(com.algalopez.ranking.user.api.User apiUser) {

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
    public com.algalopez.ranking.user.api.User mapFrom(User modelUser) {

        if (modelUser == null) {
            return null;
        }

        UserAuth modelUserAuth = modelUser.getUserAuth();
        UserInfo modelUserInfo = modelUser.getUserInfo();

        com.algalopez.ranking.user.api.User.UserBuilder userBuilder = com.algalopez.ranking.user.api.User.builder();

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
