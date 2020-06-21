package com.algalopez.tunturi.user.api;

import com.algalopez.tunturi.user.api.mapper.UserModelMapper;
import com.algalopez.tunturi.user.api.model.User;
import com.algalopez.tunturi.user.core.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Api(value = "Private user management system")
@RestController
@RequestMapping("api")
public class UserControllerRest {

    private final UserService userService;

    public UserControllerRest(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Get user information")
    @PreAuthorize("#id == authentication.principal.id")
    @GetMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable(name = "id", required = true) Long id) {

        User user = new UserModelMapper().mapFrom(userService.findUserById(id));
        return ResponseEntity.ok(user);
    }

    @ApiOperation(value = "Update user information")
    @PreAuthorize("#user.id == authentication.principal.id")
    @PutMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateUser(@RequestBody User user) {

        com.algalopez.tunturi.user.core.model.User userModel = new UserModelMapper().mapTo(user);
        userService.updateUserInfo(userModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Change users password")
    @PreAuthorize("#id == authentication.principal.id")
    @PatchMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> patchUserPassword(@PathVariable(name = "id", required = true) Long id, @RequestBody String password) {

        userService.patchUserPassword(id, password);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
