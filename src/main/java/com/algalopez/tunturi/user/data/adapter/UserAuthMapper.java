package com.algalopez.tunturi.user.data.adapter;

import com.algalopez.tunturi.common.Mapper;

import com.algalopez.tunturi.user.core.model.UserRole;
import com.algalopez.tunturi.user.data.model.UserAuth;
import org.springframework.stereotype.Service;

@Service
public class UserAuthMapper implements Mapper<UserAuth, com.algalopez.tunturi.user.core.model.UserAuth> {

    private static final String OBFUSCATED_PW = "***************";

    @Override
    public com.algalopez.tunturi.user.core.model.UserAuth mapTo(UserAuth dataUserAuth) {

        if (dataUserAuth == null) {
            return null;
        }

        return com.algalopez.tunturi.user.core.model.UserAuth.builder()
                .id(dataUserAuth.getId())
                .username(dataUserAuth.getUsername())
                .password(OBFUSCATED_PW)
                .enabled(dataUserAuth.isEnabled())
                .locked(dataUserAuth.isLocked())
                .role(UserRole.parseUserRole(dataUserAuth.getRole()))
                .build();
    }

    @Override
    public UserAuth mapFrom(com.algalopez.tunturi.user.core.model.UserAuth modelUserAuth) {

        if (modelUserAuth == null) {
            return null;
        }

        String parsedRole = modelUserAuth.getRole() == null ? null : modelUserAuth.getRole().getRoleValue();
        return com.algalopez.tunturi.user.data.model.UserAuth.builder()
                .id(modelUserAuth.getId())
                .username(modelUserAuth.getUsername())
                .password(modelUserAuth.getPassword())
                .enabled(modelUserAuth.isEnabled())
                .locked(modelUserAuth.isLocked())
                .role(parsedRole)
                .build();
    }
}
