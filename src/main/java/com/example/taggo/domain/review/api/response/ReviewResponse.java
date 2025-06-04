package com.example.taggo.domain.review.api.response;

import com.example.taggo.domain.review.model.Review;

import java.util.List;

public record ReviewResponse(
        Long id,
        Double rating,
        String content
){
    public static ReviewResponse from(Review review) {
        return new ReviewResponse(
                review.getId(),
                review.getRating(),
                review.getContent()
        );
    }
}
