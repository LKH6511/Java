package com.example.cmd.domain.controller;

import com.example.cmd.domain.controller.dto.request.LoginRequest;
import com.example.cmd.domain.controller.dto.request.SignupRequest;
import com.example.cmd.domain.service.UserService;
import com.example.cmd.global.security.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("signup")
    public void signup(@RequestBody SignupRequest signupRequest) {
        userService.signUp(signupRequest);
    }

    @PostMapping("login")
    public TokenResponse login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }
}