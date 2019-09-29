package com.algalopez.ranking.auth.api;

import com.algalopez.ranking.RankingApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RankingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Transactional
@Slf4j
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthApiTest {

    private static final String PUBLIC_ENDPOINT = "/api/public/test/sample/";
    private static final String PRIVATE_ENDPOINT = "/api/test/sample/";
    private static final String ADMIN_ENDPOINT = "/api/admin/test/sample/";

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @WithAnonymousUser
    @Test
    public void testPublicEndpointWithAnonymousUser() throws Exception {

        mvc.perform(get(PUBLIC_ENDPOINT + "user1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"sample\":\"user1\"}"));
    }

    @WithAnonymousUser
    @Test
    public void testPrivateEndpointWithAnonymousUser() throws Exception {

        mvc.perform(get(PRIVATE_ENDPOINT + "user1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @WithMockUser(value = "other_user", roles = "USER")
    @Test
    public void testPrivateEndpointWithInvalidUser() throws Exception {

        mvc.perform(get(PRIVATE_ENDPOINT + "user1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(value = "user1", roles = "USER")
    @Test
    public void testPrivateEndpointWithValidUser() throws Exception {

        mvc.perform(get(PRIVATE_ENDPOINT + "user1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"sample\":\"user1\"}"));
    }

    @WithAnonymousUser
    @Test
    public void testAdminEndpointWithAnonymousUser() throws Exception {

        mvc.perform(get(ADMIN_ENDPOINT + "user1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @WithMockUser(value = "user1", roles = "USER")
    @Test
    public void testAdminEndpointWithValidUser() throws Exception {

        mvc.perform(get(ADMIN_ENDPOINT + "user1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(value = "other_admin", roles = "ADMIN")
    @Test
    public void testAdminEndpointWithAdmin() throws Exception {

        mvc.perform(get(ADMIN_ENDPOINT + "user1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"sample\":\"user1\"}"));
    }
}
