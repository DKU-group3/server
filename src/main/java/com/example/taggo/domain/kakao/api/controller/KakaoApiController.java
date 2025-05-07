package com.example.taggo.domain.kakao.api.controller;

import com.example.taggo.domain.kakao.api.response.SearchResultListResponse;
import com.example.taggo.domain.kakao.service.KakaoApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/search")
@RequiredArgsConstructor
public class KakaoApiController {

    private final KakaoApiService kakaoApiService;

    @GetMapping
    public ResponseEntity<SearchResultListResponse> search(@RequestParam String query) {
        return ResponseEntity.ok(kakaoApiService.keywordSearch(query));
    }
}
