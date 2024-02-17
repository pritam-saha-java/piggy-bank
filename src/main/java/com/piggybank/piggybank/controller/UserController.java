package com.piggybank.piggybank.controller;

import com.piggybank.piggybank.entity.UserEntity;
import com.piggybank.piggybank.request.CreateUserRequest;
import com.piggybank.piggybank.response.CreateUserResponse;
import com.piggybank.piggybank.response.FindAllUsersResponse;
import com.piggybank.piggybank.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/find/all", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<FindAllUsersResponse> findAll() {
        List<UserEntity> userList = userService.findAll();

        FindAllUsersResponse response = new FindAllUsersResponse();
        response.setUserList(userList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "/create",produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request) {
        return new ResponseEntity<>(userService.createUser(request), HttpStatus.CREATED);
    }

}
