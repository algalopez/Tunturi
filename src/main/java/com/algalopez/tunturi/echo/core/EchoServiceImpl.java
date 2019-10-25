package com.algalopez.tunturi.echo.core;

import org.springframework.stereotype.Service;

@Service
public class EchoServiceImpl implements EchoService {

    @Override
    public String echo(String message) {
        return message;
    }
}
