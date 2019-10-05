package com.algalopez.ranking.user.unit.model;

import com.algalopez.ranking.user.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class UserRoleUnitTest {

    private static final String ROLE_USER = "USER";
    private static final String ROLE_ADMIN = "ADMIN";

    @Test
    public void testParseUserRole() {

        assertEquals(UserRole.USER, UserRole.parseUserRole(ROLE_USER));
        assertEquals(UserRole.ADMIN, UserRole.parseUserRole(ROLE_ADMIN));
        assertNull(UserRole.parseUserRole(null));
    }

    @Test
    public void testFormatUserRole() {
        assertEquals(ROLE_USER, UserRole.USER.getRoleValue());
        assertEquals(ROLE_ADMIN, UserRole.ADMIN.getRoleValue());
    }
}
