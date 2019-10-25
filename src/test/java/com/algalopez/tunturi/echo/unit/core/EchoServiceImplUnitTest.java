package com.algalopez.tunturi.echo.unit.core;

import com.algalopez.tunturi.echo.core.EchoService;
import com.algalopez.tunturi.echo.core.EchoServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class EchoServiceImplUnitTest {

    private EchoService echoService;

    @Before
    public void init() {
        this.echoService = new EchoServiceImpl();
    }

    @Test
    public void testEcho() {
        final String message = "example message";
        assertEquals(message,echoService.echo(message));
    }
}
