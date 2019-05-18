package com.ranking.sample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("web")
@Slf4j
public class SampleWebController {

    private static final String SAMPLE_WEB  = "sample";
    private static final String ATTRIBUTE_ID = "id";
    private static final String ATTRIBUTE_SAMPLE = "sample";

    private SampleService sampleService;

    @Autowired
    public SampleWebController(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @GetMapping(value = "/public/sample/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String publicSample(@PathVariable(name = "username", required = true) String username, Model model) {

        SampleDto sampleDto = sampleService.getSample(1);
        model.addAttribute(ATTRIBUTE_ID, sampleDto.getId());
        model.addAttribute(ATTRIBUTE_SAMPLE, sampleDto.getSample());
        return SAMPLE_WEB;
    }

    @PreAuthorize("#username == authentication.principal.username")
    @GetMapping(value = "/sample/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String privateSample(@PathVariable(name = "username", required = true) String username, Model model) {

        SampleDto sampleDto = sampleService.getSample(2);
        model.addAttribute(ATTRIBUTE_ID, sampleDto.getId());
        model.addAttribute(ATTRIBUTE_SAMPLE, sampleDto.getSample());
        return SAMPLE_WEB;
    }

    @GetMapping(value = "/admin/sample/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String adminSample(@PathVariable(name = "username", required = true) String username, Model model) {

        SampleDto sampleDto = sampleService.getSample(3);
        model.addAttribute(ATTRIBUTE_ID, sampleDto.getId());
        model.addAttribute(ATTRIBUTE_SAMPLE, sampleDto.getSample());
        return SAMPLE_WEB;
    }
}
