package com.algalopez.ranking.auth.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@Slf4j
public class AuthSampleController {

    private UserDetailsService authService;

    @GetMapping(value = "/public/test/sample/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthSampleDto> publicSample(@PathVariable(name = "username", required = true) String username) {

        log.debug("Getting public sample");
        AuthSampleDto authSampleDto = new AuthSampleDto(username);
        return ResponseEntity.ok(authSampleDto);
    }

    @PreAuthorize("#username == authentication.principal.username")
    @GetMapping(value = "/test/sample/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthSampleDto> privateSample(@PathVariable(name = "username", required = true) String username) {

        log.debug("Getting private sample");
        AuthSampleDto authSampleDto = new AuthSampleDto(username);
        return ResponseEntity.ok(authSampleDto);
    }

    @GetMapping(value = "/admin/test/sample/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthSampleDto> adminSample(@PathVariable(name = "username", required = true) String username) {

        log.debug("Getting admin sample");
        AuthSampleDto authSampleDto = new AuthSampleDto(username);
        return ResponseEntity.ok(authSampleDto);
    }
}
