package com.example.cmd.domain.service;

import com.example.cmd.domain.controller.dto.request.LoginRequest;
import com.example.cmd.domain.controller.dto.request.SignupRequest;
import com.example.cmd.domain.entity.User;
import com.example.cmd.domain.repository.UserRepository;
import com.example.cmd.domain.service.exception.PasswordMismatchException;
import com.example.cmd.domain.service.exception.UserAlreadyExistException;
import com.example.cmd.global.security.TokenResponse;
import com.example.cmd.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public void signUp(SignupRequest signupRequest) {

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw UserAlreadyExistException.EXCEPTION;
        }

        userRepository.save(
                User.builder()
                        .email(signupRequest.getEmail())
                        .name(signupRequest.getName())
                        .nickName(signupRequest.getNickName())
                        .password(signupRequest.getPassword())
                        .build()
        );
    }

    @Transactional
    public TokenResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalStateException());

        if (!request.getPassword().equals(user.getPassword())) {
            throw PasswordMismatchException.EXCEPTION;
        }

        return TokenResponse
                .builder()
                .accessToken(jwtTokenProvider.createAccessToken(user.getEmail()))
                .build();
    }

}
