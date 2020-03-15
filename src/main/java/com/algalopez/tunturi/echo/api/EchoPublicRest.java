package com.algalopez.tunturi.echo.api;

import com.algalopez.tunturi.echo.core.EchoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Echo server endpoint")
@RestController
@RequestMapping("api/public")
public class EchoPublicRest {

    private static final String PARAMETER_NAME = "message";

    private EchoService echoService;

    public EchoPublicRest(EchoService echoService) {
        this.echoService = echoService;
    }

    @ApiOperation(value = "Echo a message")
    @GetMapping(value = "/echo")
    public ResponseEntity<String> echo(@RequestParam(PARAMETER_NAME) String message) {
        String echoedMessage = echoService.echo(message);
        return ResponseEntity.ok(echoedMessage);
    }
}
