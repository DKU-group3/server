package com.example.taggo.importer.request;

public record ReviewImportRequest(
        Double rating,
        String comment,
        String imgUrl
) {}
