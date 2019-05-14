package com.ranking.sample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("api")
@Slf4j
public class SampleJsonController {

    private SampleService sampleService;

    @Autowired
    public SampleJsonController(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @GetMapping(value = "/public/sample/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SampleDto> publicSample() {

        SampleDto sampleDto = sampleService.getSample(1);
        return ResponseEntity.ok(sampleDto);
    }

    @GetMapping(value = "/sample/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SampleDto> privateSample() {

        SampleDto sampleDto = sampleService.getSample(1);
        return ResponseEntity.ok(sampleDto);
    }

    // Principal principal
    @GetMapping(value = "/admin/sample/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SampleDto> adminSample() {

        SampleDto sampleDto = sampleService.getSample(1);
        return ResponseEntity.ok(sampleDto);
    }
}
