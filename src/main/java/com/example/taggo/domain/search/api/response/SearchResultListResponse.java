package com.example.taggo.domain.search.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record SearchResultListResponse(
        @JsonProperty("documents")
        List<SearchResultResponse> result
) {
}
