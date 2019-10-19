package com.algalopez.tunturi.user.api;

import com.algalopez.tunturi.user.UserService;
import com.algalopez.tunturi.user.api.mapper.UserModelMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "Admin user management system")
@RestController
@RequestMapping("api/admin")
@Slf4j
public class UserControllerRestAdmin {

    private UserService userService;

    public UserControllerRestAdmin(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Get user information")
    @GetMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserAsAdmin(@PathVariable(name = "id", required = true) Long id) {

        User user = new UserModelMapper().mapFrom(userService.findUserById(id));
        return ResponseEntity.ok(user);
    }

    @ApiOperation(value = "Update user information")
    @PutMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUserAsAdmin(@RequestBody User user) {

        com.algalopez.tunturi.user.model.User userModel = new UserModelMapper().mapTo(user);
        userService.updateUser(userModel);
        return ResponseEntity.ok(user);
    }
}
