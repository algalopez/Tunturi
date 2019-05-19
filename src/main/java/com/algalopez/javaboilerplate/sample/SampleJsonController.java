package com.algalopez.javaboilerplate.sample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@Slf4j
public class SampleJsonController {

    private SampleService sampleService;

    @Autowired
    public SampleJsonController(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @GetMapping(value = "/public/com.algalopez.javaboilerplate.sample/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SampleDto> publicSample(@PathVariable(name = "username", required = true) String username) {

        log.debug("Getting public com.algalopez.javaboilerplate.sample");
        SampleDto sampleDto = sampleService.getSample(1);
        return ResponseEntity.ok(sampleDto);
    }

    @PreAuthorize("#username == authentication.principal.username")
    @GetMapping(value = "/com.algalopez.javaboilerplate.sample/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SampleDto> privateSample(@PathVariable(name = "username", required = true) String username) {

        log.debug("Getting private com.algalopez.javaboilerplate.sample");
        SampleDto sampleDto = sampleService.getSample(1);
        return ResponseEntity.ok(sampleDto);
    }

    @GetMapping(value = "/admin/com.algalopez.javaboilerplate.sample/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SampleDto> adminSample(@PathVariable(name = "username", required = true) String username) {

        log.debug("Getting admin com.algalopez.javaboilerplate.sample");
        SampleDto sampleDto = sampleService.getSample(1);
        return ResponseEntity.ok(sampleDto);
    }
}
