package com.example.taggo.importer.request;

import java.util.List;

public record PlaceImportRequest(
        String storeName,
        String storeImg,
        List<String> tags,
        List<ReviewImportRequest> reviews
) {}