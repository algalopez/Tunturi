package com.algalopez.tunturi.user.model.mapper;

import com.algalopez.tunturi.common.Mapper;
import com.algalopez.tunturi.user.model.*;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
public class UserDataMapper implements Mapper<User, UserDataMapper.DataUser> {

    private static final String OBFUSCATED_PW = "***************";

    @Override
    public DataUser mapTo(User user) {

        com.algalopez.tunturi.user.data.UserAuth dataUserAuth = toUserAuth(user.getUserAuth());
        com.algalopez.tunturi.user.data.UserInfo dataUserInfo = toUserInfo(user.getUserInfo());

        return new DataUser(dataUserAuth, dataUserInfo);
    }

    @Override
    public User mapFrom(DataUser dataUser) {

        UserAuth modelUserAuth = fromUserAuth(dataUser.getUserAuth());
        UserInfo modelUserInfo = fromUserInfo(dataUser.getUserInfo());

        return User.builder()
                .userAuth(modelUserAuth)
                .userInfo(modelUserInfo)
                .build();
    }

    private com.algalopez.tunturi.user.data.UserInfo toUserInfo(UserInfo modelUserInfo) {

        if (modelUserInfo == null) {
            return null;
        }

        return com.algalopez.tunturi.user.data.UserInfo.builder()
                .id(modelUserInfo.getId())
                .username(modelUserInfo.getUsername())
                .email(modelUserInfo.getEmail())
                .level(modelUserInfo.getLevel())
                .build();
    }

    private com.algalopez.tunturi.user.data.UserAuth toUserAuth(UserAuth modelUserAuth) {

        if (modelUserAuth == null) {
            return null;
        }

        String parsedRole = modelUserAuth.getRole() == null ? null : modelUserAuth.getRole().getRoleValue();
        return com.algalopez.tunturi.user.data.UserAuth.builder()
                .id(modelUserAuth.getId())
                .username(modelUserAuth.getUsername())
                .password(modelUserAuth.getPassword())
                .enabled(modelUserAuth.isEnabled())
                .locked(modelUserAuth.isLocked())
                .role(parsedRole)
                .build();
    }

    private UserAuth fromUserAuth(com.algalopez.tunturi.user.data.UserAuth dataUserAuth) {

        if (dataUserAuth == null) {
            return null;
        }

        return UserAuth.builder()
                .id(dataUserAuth.getId())
                .username(dataUserAuth.getUsername())
                .password(OBFUSCATED_PW)
                .enabled(dataUserAuth.isEnabled())
                .locked(dataUserAuth.isLocked())
                .role(UserRole.parseUserRole(dataUserAuth.getRole()))
                .build();

    }

    private UserInfo fromUserInfo(com.algalopez.tunturi.user.data.UserInfo dataUserInfo) {

        if (dataUserInfo == null) {
            return null;
        }

        return UserInfo.builder()
                .id(dataUserInfo.getId())
                .username(dataUserInfo.getUsername())
                .email(dataUserInfo.getEmail())
                .level(dataUserInfo.getLevel())
                .build();
    }

    @Getter
    public static class DataUser {
        private com.algalopez.tunturi.user.data.UserAuth userAuth;
        private com.algalopez.tunturi.user.data.UserInfo userInfo;

        public DataUser(com.algalopez.tunturi.user.data.UserAuth userAuth, com.algalopez.tunturi.user.data.UserInfo userInfo) {
            this.userAuth = userAuth;
            this.userInfo = userInfo;
        }
    }
}