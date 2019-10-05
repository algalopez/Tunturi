package com.algalopez.ranking.user.api;

import com.algalopez.ranking.RankingApplication;
import com.algalopez.ranking.auth.Auth;
import com.algalopez.ranking.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RankingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Transactional
@Slf4j
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserApiTest {

    private static final String GET_USER_ENDPOINT = "/api/users/{id}";
    private static final String UPDATE_USER_ENDPOINT = "/api/users";
    private static final String PATCH_USER_PASSWORD_ENDPOINT = "/api/users/{id}";
    private static final Long ID = 1L;
    private static final int ONCE = 1;

    @MockBean
    private UserService userService;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetUser() throws Exception {

        when(userService.findUserById(any())).thenReturn(null);

        List<SimpleGrantedAuthority> role = Collections.singletonList(new SimpleGrantedAuthority("USER"));
        mvc.perform(get(GET_USER_ENDPOINT, ID)
                .with(user(new Auth(ID, "user1", "b", true, true, role)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateUser() throws Exception {

        doNothing().when(userService).updateUserInfo(any(com.algalopez.ranking.user.model.User.class));

        List<SimpleGrantedAuthority> role = Collections.singletonList(new SimpleGrantedAuthority("USER"));
        mvc.perform(put(UPDATE_USER_ENDPOINT)
                .content(objectMapper.writeValueAsString(buildUser()))
                .with(user(new Auth(ID, "user1", "b", true, true, role)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testPatchUserPassword() throws Exception {

        String password = "pass";
        doNothing().when(userService).patchUserPassword(ID, password);

        List<SimpleGrantedAuthority> role = Collections.singletonList(new SimpleGrantedAuthority("USER"));
        mvc.perform(patch(PATCH_USER_PASSWORD_ENDPOINT, ID)
                .content(password)
                .with(user(new Auth(ID, "user1", "b", true, true, role)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userService, times(ONCE)).patchUserPassword(ID, password);
    }

    private User buildUser() {
        return User.builder()
                .id(ID)
                .username("user")
                .email("email")
                .password("password")
                .enabled(true)
                .locked(false)
                .role("USER")
                .level(4)
                .build();
    }

}
