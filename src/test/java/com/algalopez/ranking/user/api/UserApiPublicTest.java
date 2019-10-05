package com.algalopez.ranking.user.api;

import com.algalopez.ranking.RankingApplication;
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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RankingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Transactional
@Slf4j
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserApiPublicTest {

    private static final String POST_USER_ENDPOINT = "/api/public/users";

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
    public void testCreateUser() throws Exception {

        Long id = 1L;
        when(userService.createUser(any())).thenReturn(id);

        UserCreationRequest userCreationRequest = new UserCreationRequest("email", "password");
        mvc.perform(post(POST_USER_ENDPOINT)
                .content(objectMapper.writeValueAsString(userCreationRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(id)));

        verify(userService, times(1)).createUser(any());
    }

}
