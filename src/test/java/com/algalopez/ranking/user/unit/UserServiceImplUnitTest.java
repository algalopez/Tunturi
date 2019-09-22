package com.algalopez.ranking.user.unit;

import com.algalopez.ranking.user.data.UserAuthDao;
import com.algalopez.ranking.user.data.UserInfoDao;
import com.algalopez.ranking.user.UserService;
import com.algalopez.ranking.user.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplUnitTest {

    private static final int ONCE = 1;

    @Mock
    private UserInfoDao userInfoDao;

    @Mock
    private UserAuthDao userAuthDao;

    private UserService userService;

    @Before
    public void prepareMocks() {
        userService = new UserServiceImpl(userAuthDao, userInfoDao);
    }

    @Test
    public void testUserCreation() {

        final Long id = 1L;
        final String email = "email";
        final String password = "password";
        final String role = "USER";

        when(userAuthDao.createUserAuth(anyString(), anyString(), anyString())).thenReturn(id);
        when(userInfoDao.createUserInfo(anyLong(), anyString())).thenReturn(id);

        Long userId = userService.createUser(email, password, role);

        verify(userAuthDao, times(ONCE)).createUserAuth(email, password, role);
        verify(userInfoDao, times(ONCE)).createUserInfo(id, email);
        assertEquals(id, userId);
    }
}
