package com.example.taggo.domain.review.service;

import com.example.taggo.domain.place.service.PlaceService;
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
    public ReviewListResponse findByKakaoId(Long KakaoId){
        return ReviewListResponse.from(reviewRepository.findByPlace(
                placeService.findByKaKaoId(KakaoId))
        );
    }
}
