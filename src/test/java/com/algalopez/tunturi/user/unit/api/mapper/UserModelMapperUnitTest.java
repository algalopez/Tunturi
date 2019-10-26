package com.algalopez.tunturi.user.unit.api.mapper;

import com.algalopez.tunturi.user.api.model.User;
import com.algalopez.tunturi.user.api.mapper.UserModelMapper;
import com.algalopez.tunturi.user.core.model.UserAuth;
import com.algalopez.tunturi.user.core.model.UserInfo;
import com.algalopez.tunturi.user.core.model.UserRole;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserModelMapperUnitTest {

    private static final Long ID = 1L;
    private static final String USERNAME = "user";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "pass";
    private static final Integer LEVEL = 2;
    private static final boolean LOCKED = false;
    private static final boolean ENABLED = true;
    private static final String ROLE = UserRole.USER.getRoleValue();

    private UserModelMapper userModelMapper;

    @Before
    public void init() {

        userModelMapper = new UserModelMapper();
    }

    @Test
    public void testEmptyMapToModel() {

        assertNull(userModelMapper.mapTo(null));
    }

    @Test
    public void testFullMapToModel() {

        User apiUser = User.builder()
                .id(ID)
                .email(EMAIL)
                .username(USERNAME)
                .password(PASSWORD)
                .role(ROLE)
                .level(LEVEL)
                .enabled(ENABLED)
                .locked(LOCKED)
                .build();

        com.algalopez.tunturi.user.core.model.User modelUser = userModelMapper.mapTo(apiUser);
        UserAuth modelUserAuth = modelUser.getUserAuth();
        UserInfo modelUserInfo = modelUser.getUserInfo();

        assertEquals(ID, modelUserAuth.getId());
        assertEquals(USERNAME, modelUserAuth.getUsername());
        assertEquals(PASSWORD, modelUserAuth.getPassword());
        assertEquals(UserRole.USER, modelUserAuth.getRole());
        assertEquals(ENABLED, modelUserAuth.isEnabled());
        assertEquals(LOCKED, modelUserAuth.isLocked());
        assertEquals(ID, modelUserInfo.getId());
        assertEquals(USERNAME, modelUserInfo.getUsername());
        assertEquals(EMAIL, modelUserInfo.getEmail());
        assertEquals(LEVEL, modelUserInfo.getLevel());
    }

    @Test
    public void testFullMapFromModel() {

        UserAuth modelUserAuth = UserAuth.builder()
                .id(ID)
                .username(USERNAME)
                .password(PASSWORD)
                .enabled(ENABLED)
                .locked(LOCKED)
                .role(UserRole.parseUserRole(ROLE))
                .build();
        UserInfo modelUserInfo = UserInfo.builder()
                .id(ID)
                .username(USERNAME)
                .email(EMAIL)
                .level(LEVEL)
                .build();
        com.algalopez.tunturi.user.core.model.User modelUser = com.algalopez.tunturi.user.core.model.User.builder()
                .userAuth(modelUserAuth)
                .userInfo(modelUserInfo)
                .build();

        User apiUser = userModelMapper.mapFrom(modelUser);
        assertEquals(ID, apiUser.getId());
        assertEquals(USERNAME, apiUser.getUsername());
        assertEquals(EMAIL, apiUser.getEmail());
        assertEquals(PASSWORD, apiUser.getPassword());
        assertEquals(ENABLED, apiUser.isEnabled());
        assertEquals(LOCKED, apiUser.isLocked());
        assertEquals(ROLE, apiUser.getRole());
        assertEquals(LEVEL, apiUser.getLevel());
    }

    @Test
    public void testEmptyMapFromModel() {

        assertNull(userModelMapper.mapFrom(null));
    }

    @Test
    public void testEmptyUserAuthFromModel() {

        UserInfo modelUserInfo = UserInfo.builder()
                .id(ID)
                .username(USERNAME)
                .email(EMAIL)
                .level(LEVEL)
                .build();
        com.algalopez.tunturi.user.core.model.User modelUser = com.algalopez.tunturi.user.core.model.User.builder()
                .userAuth(null)
                .userInfo(modelUserInfo)
                .build();

        User apiUser = userModelMapper.mapFrom(modelUser);
        assertNull(apiUser.getId());
        assertNull(apiUser.getUsername());
        assertNull(apiUser.getPassword());
        assertFalse(apiUser.isEnabled());
        assertFalse(apiUser.isLocked());
        assertNull(apiUser.getRole());
        assertEquals(EMAIL, apiUser.getEmail());
        assertEquals(LEVEL, apiUser.getLevel());
    }

    @Test
    public void testEmptyUserInfoFromModel() {

        UserAuth modelUserAuth = UserAuth.builder()
                .id(ID)
                .username(USERNAME)
                .password(PASSWORD)
                .enabled(ENABLED)
                .locked(LOCKED)
                .role(UserRole.parseUserRole(ROLE))
                .build();

        com.algalopez.tunturi.user.core.model.User modelUser = com.algalopez.tunturi.user.core.model.User.builder()
                .userAuth(modelUserAuth)
                .userInfo(null)
                .build();

        User apiUser = userModelMapper.mapFrom(modelUser);
        assertEquals(ID, apiUser.getId());
        assertEquals(USERNAME, apiUser.getUsername());
        assertEquals(PASSWORD, apiUser.getPassword());
        assertEquals(ENABLED, apiUser.isEnabled());
        assertEquals(LOCKED, apiUser.isLocked());
        assertEquals(ROLE, apiUser.getRole());
        assertNull(apiUser.getEmail());
        assertNull(apiUser.getLevel());
    }
}
