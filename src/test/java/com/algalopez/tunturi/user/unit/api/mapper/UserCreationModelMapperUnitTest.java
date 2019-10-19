package com.algalopez.tunturi.user.unit.api.mapper;

import com.algalopez.tunturi.user.api.UserCreationRequest;
import com.algalopez.tunturi.user.api.mapper.UserCreationModelMapper;
import com.algalopez.tunturi.user.model.User;
import com.algalopez.tunturi.user.model.UserRole;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserCreationModelMapperUnitTest {

    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";

    private UserCreationModelMapper userCreationModelMapper;

    @Before
    public void init() {

        userCreationModelMapper = new UserCreationModelMapper();
    }

    @Test
    public void testFullMapToModel() {

        UserCreationRequest userCreationRequest = new UserCreationRequest(EMAIL, PASSWORD);
        User modelUser = userCreationModelMapper.mapTo(userCreationRequest);

        assertNull(modelUser.getUserAuth().getId());
        assertEquals(EMAIL, modelUser.getUserAuth().getUsername());
        assertEquals(PASSWORD, modelUser.getUserAuth().getPassword());
        assertTrue(modelUser.getUserAuth().isEnabled());
        assertFalse(modelUser.getUserAuth().isLocked());
        assertEquals(UserRole.USER, modelUser.getUserAuth().getRole());
        assertNull(modelUser.getUserInfo().getId());
        assertEquals(EMAIL, modelUser.getUserInfo().getUsername());
        assertEquals(EMAIL, modelUser.getUserInfo().getEmail());
        assertNull(modelUser.getUserInfo().getLevel());
    }

    @Test
    public void testEmptyMapToModel() {

        assertNull(userCreationModelMapper.mapTo(null));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testMapFromModel() {

        userCreationModelMapper.mapFrom(User.builder().build());
    }
}
