package com.example.taggo.domain.review.api.controller;

import com.example.taggo.domain.review.api.response.ReviewListResponse;
import com.example.taggo.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/places/search/reviews")
    public ResponseEntity<ReviewListResponse> getByKakaoId(@RequestParam String name){
        ReviewListResponse response = reviewService.findByName(name);
        return ResponseEntity.ok(response);
    }

}
