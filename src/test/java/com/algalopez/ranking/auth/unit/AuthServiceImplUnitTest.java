package com.algalopez.ranking.auth.unit;

import com.algalopez.ranking.auth.Auth;
import com.algalopez.ranking.auth.AuthDao;
import com.algalopez.ranking.auth.AuthServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthServiceImplUnitTest {

    @Mock
    private AuthDao authDao;

    private AuthServiceImpl userService;

    @Before
    public void prepareMocks() {
        userService = new AuthServiceImpl(authDao);
    }

    @Test
    public void testExistingUser() {

        String username = "user";
        UserDetails mockedUser = Auth.builder()
                .id(1L)
                .username(username)
                .password("dvfndsiofj")
                .enabled(true)
                .locked(false)
                .authorities(new ArrayList<>())
                .build();
        when(authDao.findUserByUsername(username)).thenReturn(mockedUser);
        UserDetails actualUser = userService.loadUserByUsername(username);
        assertEquals(mockedUser, actualUser);
        assertTrue(actualUser.isCredentialsNonExpired());
        assertTrue(actualUser.isAccountNonExpired());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testNonExistingUser() {
        String username = "user";
        when(authDao.findUserByUsername(username)).thenThrow(new EmptyResultDataAccessException(1));
        userService.loadUserByUsername(username);
    }
}
