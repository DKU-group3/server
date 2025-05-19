package com.example.taggo.domain.review.api.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AddReviewRequest(

        @NotNull
        Long kakaoId,

        @NotNull
        String placeName,

        @NotNull
        String content,

        String youtubeUrl,

        @DecimalMin(value = "0.0", inclusive = true)
        @DecimalMax(value = "5.0", inclusive = true)
        @NotNull
        Double rating,

        List<Long> tagIds

) {
}
