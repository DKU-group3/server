package com.example.taggo.domain.review.api.controller;

import com.example.taggo.domain.review.api.response.ReviewListResponse;
import com.example.taggo.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/place/{kakaoId}")
    public ResponseEntity<ReviewListResponse> getByKakaoId(@PathVariable Long kakaoId){
        ReviewListResponse response = reviewService.findByKakaoId(kakaoId);
        return ResponseEntity.ok(response);
    }

}
