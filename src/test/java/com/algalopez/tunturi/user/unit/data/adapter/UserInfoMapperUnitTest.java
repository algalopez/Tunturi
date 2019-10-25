package com.algalopez.tunturi.user.unit.data.adapter;

import com.algalopez.tunturi.user.data.adapter.UserInfoMapper;
import com.algalopez.tunturi.user.data.model.UserInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class UserInfoMapperUnitTest {

    private UserInfoMapper userInfoMapper;

    @Before
    public void init() {
        userInfoMapper = new UserInfoMapper();
    }

    @Test
    public void testFullMapToModel() {

        UserInfo dataUserInfo = buildDataUserInfo(1L, "usr1", "email1", 1);
        com.algalopez.tunturi.user.core.model.UserInfo expectedModelUserInfo = buildModelUserInfo(1L, "usr1", "email1", 1);

        com.algalopez.tunturi.user.core.model.UserInfo actualModelUserInfo = userInfoMapper.mapTo(dataUserInfo);
        assertEquals(expectedModelUserInfo, actualModelUserInfo);
    }

    @Test
    public void testNullMapToModel() {

        com.algalopez.tunturi.user.core.model.UserInfo actualDataUserInfo = userInfoMapper.mapTo(null);
        assertNull(actualDataUserInfo);
    }

    @Test
    public void testFullMapFromData() {

        com.algalopez.tunturi.user.core.model.UserInfo modelUserInfo = buildModelUserInfo(2L, "usr2", "email2", 2);
        UserInfo expectedDataUserInfo = buildDataUserInfo(2L, "usr2", "email2", 2);

        UserInfo actualDataUserInfo = userInfoMapper.mapFrom(modelUserInfo);
        assertEquals(expectedDataUserInfo, actualDataUserInfo);
    }

    @Test
    public void testNullMapFromData() {
        UserInfo actualDataUserInfo = userInfoMapper.mapFrom(null);
        assertNull(actualDataUserInfo);
    }

    private com.algalopez.tunturi.user.core.model.UserInfo buildModelUserInfo(Long id, String username, String email, Integer
            level) {

        return com.algalopez.tunturi.user.core.model.UserInfo.builder()
                .id(id)
                .email(email)
                .username(username)
                .level(level)
                .build();
    }

    private UserInfo buildDataUserInfo(Long id, String username, String email, Integer level) {

        return UserInfo.builder()
                .id(id)
                .email(email)
                .username(username)
                .level(level)
                .build();
    }



}
