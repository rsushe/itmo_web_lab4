package com.rsushe.weblab4.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AuthRequest {
    private String username;
    private String password;
}
