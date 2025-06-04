package com.example.taggo.domain.review.api.response;

import com.example.taggo.domain.review.model.Review;

import java.util.List;

public record ReviewListResponse(
        List<ReviewResponse> lists
) {
    public static ReviewListResponse from(List<Review> reviews) {
        List<ReviewResponse> responseList = reviews.stream()
                .map(ReviewResponse::from)
                .toList();

        return new ReviewListResponse(responseList);
    }
}
