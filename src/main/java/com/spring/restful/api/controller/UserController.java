package com.spring.restful.api.controller;

import com.spring.restful.api.model.RegisterUserReq;
import com.spring.restful.api.model.WebResponse;
import com.spring.restful.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
