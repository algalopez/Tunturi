package auth.unit;

import com.ranking.auth.User;
import com.ranking.auth.UserDao;
import com.ranking.auth.UserServiceImpl;
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
public class UserServiceImplUnitTest {

    @Mock
    private UserDao userDao;

    private UserServiceImpl userService;

    @Before
    public void prepareMocks() {
        userService = new UserServiceImpl(userDao);
    }

    @Test
    public void testExistingUser() {

        String username = "user";
        UserDetails mockedUser = User.builder()
                .id(1L)
                .username(username)
                .password("dvfndsiofj")
                .enabled(true)
                .locked(false)
                .authorities(new ArrayList<>())
                .build();
        when(userDao.findByUsername(username)).thenReturn(mockedUser);
        UserDetails actualUser = userService.loadUserByUsername(username);
        assertEquals(mockedUser, actualUser);
        assertTrue(actualUser.isCredentialsNonExpired());
        assertTrue(actualUser.isAccountNonExpired());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testNonExistingUser() {
        String username = "user";
        when(userDao.findByUsername(username)).thenThrow(new EmptyResultDataAccessException(1));
        userService.loadUserByUsername(username);
    }
}
