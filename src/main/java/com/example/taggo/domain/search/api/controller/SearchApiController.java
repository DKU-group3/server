package com.example.taggo.domain.search.api.controller;

import com.example.taggo.domain.search.api.request.SearchRequest;
import com.example.taggo.domain.search.api.response.SearchResultListResponse;
import com.example.taggo.domain.search.service.SearchApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/search")
@RequiredArgsConstructor
public class SearchApiController {

    private final SearchApiService searchApiService;

    @PostMapping
    public ResponseEntity<SearchResultListResponse> search(@RequestBody SearchRequest request) {
        return ResponseEntity.ok(searchApiService.keywordSearchWithTags(request.query(), request.tags()));
    }
}
