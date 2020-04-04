package com.algalopez.tunturi.echo.api;

import com.algalopez.tunturi.TunturiApplication;
import com.algalopez.tunturi.echo.core.EchoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_CLASS;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TunturiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(locations = "classpath:test.properties")
@DirtiesContext(classMode = AFTER_CLASS)
public class EchoPublicRestApiTest {

    private static final String PARAMETER_NAME = "message";
    private static final String GET_ECHO_ENDPOINT = "/api/public/echo";

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private EchoService echoService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void testEcho() throws Exception {
        String message = "example message";
        when(echoService.echo(anyString())).thenReturn(message);

        mockMvc.perform(get(GET_ECHO_ENDPOINT)
                .param(PARAMETER_NAME, message)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(message));

        verify(echoService, times(1)).echo(message);
    }

}
