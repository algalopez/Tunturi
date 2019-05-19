package com.algalopez.javaboilerplate.sample.integration;

import com.algalopez.javaboilerplate.JavaBoilerplateApplication;
import com.algalopez.javaboilerplate.sample.SampleDto;
import com.algalopez.javaboilerplate.sample.SampleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JavaBoilerplateApplication.class)
@TestPropertySource(locations = "classpath:test.properties")
@Transactional
public class SampleServiceIntegrationTest {

    @Autowired
    private SampleService sampleService;

    @Test
    public void testGetSample() {

        Integer id = 1;
        SampleDto expectedSample = new SampleDto(id, "sample 1");
        SampleDto actualSample = sampleService.getSample(id);

        assertEquals(expectedSample, actualSample);
    }
}
