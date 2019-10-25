package com.algalopez.tunturi.echo.api;

import com.algalopez.tunturi.echo.core.EchoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping(value = "/echo", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> echo(@RequestParam(PARAMETER_NAME) String message) {
        String echoedMessage = echoService.echo(message);
        return ResponseEntity.ok(echoedMessage);
    }
}
