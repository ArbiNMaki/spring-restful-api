package com.spring.restful.api.service;

import com.spring.restful.api.entity.User;
import com.spring.restful.api.model.RegisterUserReq;
import com.spring.restful.api.model.UpdateUserReq;
import com.spring.restful.api.model.UserResponse;
import com.spring.restful.api.repository.UserRepository;
import com.spring.restful.api.security.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public void register(RegisterUserReq req) {
        validationService.validate(req);

        if (userRepository.existsById(req.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already registered");
        }

        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(BCrypt.hashpw(req.getPassword(), BCrypt.gensalt()));
        user.setName(req.getName());

        userRepository.save(user);
    }

    public UserResponse get(User user) {
        return UserResponse.builder()
                .username(user.getUsername())
                .name(user.getName())
                .build();
    }

    @Transactional
    public UserResponse update(User user, UpdateUserReq req) {
        validationService.validate(req);

        if (Objects.nonNull(req.getName())) {
            user.setName(req.getName());
        }

        if (Objects.nonNull(req.getPassword())) {
            user.setPassword(BCrypt.hashpw(req.getPassword(), BCrypt.gensalt()));
        }

        userRepository.save(user);

        return UserResponse.builder()
                .name(user.getName())
                .username(user.getUsername())
                .build();
    }
}
