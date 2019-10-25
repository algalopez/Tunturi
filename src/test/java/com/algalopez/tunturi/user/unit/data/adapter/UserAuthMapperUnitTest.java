package com.algalopez.tunturi.user.unit.data.adapter;

import com.algalopez.tunturi.user.core.model.UserRole;
import com.algalopez.tunturi.user.data.adapter.UserAuthMapper;
import com.algalopez.tunturi.user.data.model.UserAuth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class UserAuthMapperUnitTest {

    private UserAuthMapper userAuthMapper;

    @Before
    public void init() {
        userAuthMapper = new UserAuthMapper();
    }

    @Test
    public void testFullMapToData() {

        UserAuth dataUserAuth = buildDataUserAuth(1L, "usr1", "pass1", true, false, UserRole.USER.getRoleValue());
        com.algalopez.tunturi.user.core.model.UserAuth expectedModelUserAuth = buildModelUserAuth(1L, "usr1", "***************", true, false, UserRole.USER);

        com.algalopez.tunturi.user.core.model.UserAuth actualModelUserAuth = userAuthMapper.mapTo(dataUserAuth);
        assertEquals(expectedModelUserAuth, actualModelUserAuth);
    }

    @Test
    public void testEmptyMapToData() {
        com.algalopez.tunturi.user.core.model.UserAuth actualModelUserAuth = userAuthMapper.mapTo(null);
        assertNull(actualModelUserAuth);
    }

    @Test
    public void testFullMapFromData() {

        com.algalopez.tunturi.user.core.model.UserAuth modelUserAuth = buildModelUserAuth(2L, "usr2", "pass2", false, true, UserRole.ADMIN);
        UserAuth expectedDataUserAuth = buildDataUserAuth(2L, "usr2", "pass2", false, true, UserRole.ADMIN.getRoleValue());

        UserAuth actualDataUserAuth = userAuthMapper.mapFrom(modelUserAuth);
        assertEquals(expectedDataUserAuth, actualDataUserAuth);
    }

    @Test
    public void testEmptyMapFromData() {

        UserAuth actualDataUserAuth = userAuthMapper.mapFrom(null);
        assertNull(actualDataUserAuth);
    }

    @Test
    public void testNullUserRoleMapFromData() {

        com.algalopez.tunturi.user.core.model.UserAuth modelUserAuth = buildModelUserAuth(2L, "usr2", "pass2", false, true, null);
        UserAuth expectedDataUserAuth = buildDataUserAuth(2L, "usr2", "pass2", false, true, null);

        UserAuth actualDataUserAuth = userAuthMapper.mapFrom(modelUserAuth);
        assertEquals(expectedDataUserAuth, actualDataUserAuth);
    }

    private com.algalopez.tunturi.user.core.model.UserAuth buildModelUserAuth(Long id, String username, String password, Boolean enabled, Boolean locked, UserRole role) {
        return com.algalopez.tunturi.user.core.model.UserAuth.builder()
                .id(id)
                .username(username)
                .password(password)
                .enabled(enabled)
                .locked(locked)
                .role(role)
                .build();
    }

    private UserAuth buildDataUserAuth(Long id, String username, String password, Boolean enabled, Boolean locked, String role) {
        return UserAuth.builder()
                .id(id)
                .username(username)
                .password(password)
                .enabled(enabled)
                .locked(locked)
                .role(role)
                .build();
    }
}
