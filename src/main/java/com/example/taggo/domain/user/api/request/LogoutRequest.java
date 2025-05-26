package com.example.taggo.domain.user.api.request;

import lombok.Data;

@Data
public class LogoutRequest {
    private String accessToken;
    private String refreshToken;
}
