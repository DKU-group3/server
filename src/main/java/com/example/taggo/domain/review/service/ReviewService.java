package com.example.taggo.domain.review.service;

import com.example.taggo.domain.place.model.Place;
import com.example.taggo.domain.place.service.PlaceService;
import com.example.taggo.domain.review.model.Review;
import com.example.taggo.domain.review.api.response.ReviewListResponse;
import com.example.taggo.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final PlaceService placeService;

    @Transactional(readOnly = true)
    public ReviewListResponse findByName(String name) {
        return ReviewListResponse.from(placeService.findByName(name), reviewRepository.findByPlace(
                placeService.findByName(name))
        );
    }

    public Review create(Place place, Double rating, String content) {
        return reviewRepository.save(
                Review.create(place, rating, content)
        );
    }

}
