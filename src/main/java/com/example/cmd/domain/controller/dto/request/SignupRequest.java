package com.example.cmd.domain.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor()
public class SignupRequest {
    private String nickName;
    private String name;
    private String email;
    private String password;
}
