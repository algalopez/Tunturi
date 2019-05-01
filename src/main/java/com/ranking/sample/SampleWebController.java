package com.ranking.sample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.UUID;

@Controller
@Slf4j
public class SampleWebController {

    private SampleService sampleService;

    @Autowired
    public SampleWebController(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @GetMapping(value = {"web/sample"})
    public String sample(Model model) {

        UUID uuid = UUID.randomUUID();

        log.info("Request  {} web/sample/: {}", uuid);
        SampleDto sampleDto = sampleService.getSample(1);
        log.info("Response {} web/sample/: {}", uuid, sampleDto);

        model.addAttribute("id", sampleDto.getId());
        model.addAttribute("sample", sampleDto.getSample());
        return "sample";
    }
}
