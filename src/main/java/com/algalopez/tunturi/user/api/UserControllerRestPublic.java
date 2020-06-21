package com.algalopez.tunturi.user.api;

import com.algalopez.tunturi.user.api.model.UserCreationRequest;
import com.algalopez.tunturi.user.core.UserService;
import com.algalopez.tunturi.user.api.mapper.UserCreationModelMapper;
import com.algalopez.tunturi.user.core.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Public user management system")
@RestController
@RequestMapping("api/public")
@Slf4j
public class UserControllerRestPublic {

    private final UserService userService;

    public UserControllerRestPublic(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Create a new user")
    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createUserAsAnon(@RequestBody UserCreationRequest userCreationRequest) {

        User userModel = new UserCreationModelMapper().mapTo(userCreationRequest);
        Long id = userService.createUser(userModel);
        return ResponseEntity.ok(id);
    }

}
