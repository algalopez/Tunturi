package com.algalopez.tunturi.user.data.adapter;

import com.algalopez.tunturi.common.Mapper;
import com.algalopez.tunturi.user.data.model.UserInfo;
import org.springframework.stereotype.Service;

@Service
public class UserInfoMapper implements Mapper<UserInfo, com.algalopez.tunturi.user.core.model.UserInfo> {

    @Override
    public com.algalopez.tunturi.user.core.model.UserInfo mapTo(UserInfo dataUserInfo) {

        if (dataUserInfo == null) {
            return null;
        }

        return com.algalopez.tunturi.user.core.model.UserInfo.builder()
                .id(dataUserInfo.getId())
                .username(dataUserInfo.getUsername())
                .email(dataUserInfo.getEmail())
                .level(dataUserInfo.getLevel())
                .build();
    }

    @Override
    public UserInfo mapFrom(com.algalopez.tunturi.user.core.model.UserInfo modelUserInfo) {

        if (modelUserInfo == null) {
            return null;
        }

        return UserInfo.builder()
                .id(modelUserInfo.getId())
                .username(modelUserInfo.getUsername())
                .email(modelUserInfo.getEmail())
                .level(modelUserInfo.getLevel())
                .build();
    }
}
