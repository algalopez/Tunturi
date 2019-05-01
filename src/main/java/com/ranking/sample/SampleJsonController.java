package com.ranking.sample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/sample")
@Slf4j
public class SampleJsonController {

    private SampleService sampleService;

    @Autowired
    public SampleJsonController(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SampleDto> sample() {

        UUID uuid = UUID.randomUUID();

        log.info("Request  {} api/sample/: {}", uuid);
        SampleDto sampleDto = sampleService.getSample(1);
        log.info("Response {} api/sample/: {}", uuid, sampleDto);
        return new ResponseEntity<>(sampleDto, HttpStatus.OK);
    }
}
