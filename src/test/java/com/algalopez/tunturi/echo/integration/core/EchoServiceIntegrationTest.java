package com.algalopez.tunturi.echo.integration.core;

import com.algalopez.tunturi.TunturiApplication;
import com.algalopez.tunturi.echo.core.EchoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TunturiApplication.class)
@TestPropertySource(locations = "classpath:test.properties")
public class EchoServiceIntegrationTest {

    private static final int TEST_REPETITIONS = 300;

    @Autowired
    private EchoService echoService;

    @Test
    public void testEcho() {
        final String message = "Test message";
        String echoedMessage = echoService.echo(message);
        assertEquals(message, echoedMessage);
    }

    @Test
    public void testEcho_multipleRepetitions() {
        for (int j = 0; j < TEST_REPETITIONS; j++) {
            String message = "test " + j;
            String actualMessage = echoService.echo(message);
            assertEquals(message, actualMessage);
        }
    }
}
