package com.spring.restful.api.controller;

import com.spring.restful.api.entity.User;
import com.spring.restful.api.model.RegisterUserReq;
import com.spring.restful.api.model.UpdateUserReq;
import com.spring.restful.api.model.UserResponse;
import com.spring.restful.api.model.WebResponse;
import com.spring.restful.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/api/users",
                 consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<String> register(@RequestBody RegisterUserReq req) {
        userService.register(req);
        return WebResponse.<String>builder().data("Ok").build();
    }

    @GetMapping(path = "/api/users/current", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<UserResponse> get(User user) {
        UserResponse userResponse = userService.get(user);
        return WebResponse.<UserResponse>builder().data(userResponse).build();
    }

    @PatchMapping(path = "/api/users/current",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<UserResponse> update(User user, @RequestBody UpdateUserReq req) {
        UserResponse userResponse = userService.update(user, req);
        return WebResponse.<UserResponse>builder().data(userResponse).build();
    }
}
