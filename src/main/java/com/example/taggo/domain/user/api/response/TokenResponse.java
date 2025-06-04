package com.example.taggo.domain.user.api.response;

public record TokenResponse(
        String accessToken,
        String refreshToken
){
    public static TokenResponse from(String accessToken, String refreshToken) {
        return new TokenResponse(accessToken, refreshToken);
    }
} 