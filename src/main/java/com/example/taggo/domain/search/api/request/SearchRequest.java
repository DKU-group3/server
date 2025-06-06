package com.example.taggo.domain.search.api.request;

import java.util.List;

public record SearchRequest(
        String query,
        List<String> tags
) {}