package com.example.taggo.domain.kakao.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record SearchResultListResponse(
        @JsonProperty("documents")
        List<SearchResultResponse> result
) {
}
