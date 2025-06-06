package com.example.taggo.domain.review.api.response;

import com.example.taggo.domain.place.model.Place;
import com.example.taggo.domain.review.model.Review;

import java.util.List;

public record ReviewListResponse(
        String placeName,
        String imageUrl,
        List<ReviewResponse> lists
) {
    public static ReviewListResponse from(Place place, List<Review> reviews) {
        List<ReviewResponse> responseList = reviews.stream()
                .map(ReviewResponse::from)
                .toList();

        return new ReviewListResponse(place.getName(), place.getImageUrl(), responseList);
    }
}
