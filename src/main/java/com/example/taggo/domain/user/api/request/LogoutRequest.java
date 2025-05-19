package com.example.taggo.domain.user.api.request;

import jakarta.validation.constraints.NotNull;

public record LogoutRequest(
        @NotNull
        String accessToken
) {
}
