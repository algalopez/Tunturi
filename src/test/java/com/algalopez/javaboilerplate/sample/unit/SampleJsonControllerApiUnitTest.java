package com.algalopez.javaboilerplate.sample.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.algalopez.javaboilerplate.JavaBoilerplateApplication;
import com.algalopez.javaboilerplate.sample.SampleDto;
import com.algalopez.javaboilerplate.sample.SampleJsonController;
import com.algalopez.javaboilerplate.sample.SampleService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MimeTypeUtils;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = JavaBoilerplateApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:test.properties")
public class SampleJsonControllerApiUnitTest {

    @Mock
    private SampleService sampleService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {

        SampleJsonController sampleJsonController = new SampleJsonController(sampleService);
        mockMvc = MockMvcBuilders.standaloneSetup(sampleJsonController).build();
    }

    @Test
    public void testPublicApi() throws Exception {

        final String endpoint = "/api/public/com.algalopez.javaboilerplate.sample/user";

        SampleDto sampleDto = new SampleDto(232, "example");
        when(sampleService.getSample(anyInt())).thenReturn(sampleDto);

        ObjectMapper objectMapper = new ObjectMapper();
        final String expectedResult = objectMapper.writeValueAsString(sampleDto);

        mockMvc.perform(get(endpoint).accept(MimeTypeUtils.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(expectedResult)));
    }

    @Test
    public void testPrivateApi() throws Exception {

        final String endpoint = "/api/com.algalopez.javaboilerplate.sample/user";

        SampleDto sampleDto = new SampleDto(232, "example");
        when(sampleService.getSample(anyInt())).thenReturn(sampleDto);

        ObjectMapper objectMapper = new ObjectMapper();
        final String expectedResult = objectMapper.writeValueAsString(sampleDto);

        mockMvc.perform(get(endpoint).accept(MimeTypeUtils.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(expectedResult)));
    }

    @Test
    public void testAdminApi() throws Exception {

        final String endpoint = "/api/admin/com.algalopez.javaboilerplate.sample/user";

        SampleDto sampleDto = new SampleDto(232, "example");
        when(sampleService.getSample(anyInt())).thenReturn(sampleDto);

        ObjectMapper objectMapper = new ObjectMapper();
        final String expectedResult = objectMapper.writeValueAsString(sampleDto);

        mockMvc.perform(get(endpoint).accept(MimeTypeUtils.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(expectedResult)));
    }
}
