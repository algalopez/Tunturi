package com.algalopez.tunturi.user.api;

import com.algalopez.tunturi.TunturiApplication;
import com.algalopez.tunturi.user.api.model.User;
import com.algalopez.tunturi.user.core.UserService;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TunturiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Transactional
@TestPropertySource(locations = "classpath:test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserApiAdminTest {

    private static final String GET_USER_ENDPOINT = "/api/admin/users/{id}";
    private static final String UPDATE_USER_ENDPOINT = "/api/admin/users";
    private static final List<SimpleGrantedAuthority> ADMIN_ROLE = Collections.singletonList(new SimpleGrantedAuthority("ADMIN"));
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

    @WithMockUser(value = "admin", roles = "ADMIN")
    @Test
    public void testGetUserAsAdmin() throws Exception {

        Long id = 1L;
        when(userService.findUserById(any())).thenReturn(null);

        mvc.perform(get(GET_USER_ENDPOINT, id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userService, times(ONCE)).findUserById(id);
    }

    @WithMockUser(value = "admin", roles = "ADMIN")
    @Test
    public void testUpdateUserAsAdmin() throws Exception {

        doNothing().when(userService).updateUser(any());

        mvc.perform(put(UPDATE_USER_ENDPOINT)
                .content(objectMapper.writeValueAsString(buildUser()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userService, times(ONCE)).updateUser(any());
    }

    private User buildUser() {
        return User.builder()
                .id(1L)
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
