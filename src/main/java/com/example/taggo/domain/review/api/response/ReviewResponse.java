package com.example.taggo.domain.review.api.response;

import com.example.taggo.domain.review.model.Review;

public record ReviewResponse(
        Long id,
        Double rating,
        String content,
        String youtubeUrl
){
    public static ReviewResponse from(Review review) {
        return new ReviewResponse(
                review.getId(),
                review.getRating(),
                review.getContent(),
                review.getYoutubeUrl());
    }
}
