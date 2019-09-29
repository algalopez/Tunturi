package com.algalopez.ranking.user.api;

import com.algalopez.ranking.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@Slf4j
public class UserControllerRestImpl {

    private UserService userService;

    public UserControllerRestImpl(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("#id == authentication.principal.id")
    @GetMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getUser(@PathVariable(name = "id", required = true) Long id) {
        log.debug("Getting user information");
        log.debug("Principal: {}", SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        userService.findUserById(id);
        return ResponseEntity.ok("asd");
    }

}
