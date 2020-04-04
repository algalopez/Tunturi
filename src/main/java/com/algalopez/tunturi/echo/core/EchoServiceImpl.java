package com.algalopez.tunturi.echo.core;

import com.algalopez.tunturi.common.annotation.Metric;
import org.springframework.stereotype.Service;

@Service
public class EchoServiceImpl implements EchoService {

    private static final String ECHO_METRIC = "echo";

    @Metric(ECHO_METRIC)
    @Override
    public String echo(String message) {
        return message;
    }
}
