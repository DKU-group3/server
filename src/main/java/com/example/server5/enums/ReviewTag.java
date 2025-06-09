package com.example.server5.enums;

public enum ReviewTag {
    TASTE("맛"),
    KINDNESS("친절함"),
    VALUE("가성비"),
    SERVICE("서비스");

    private final String description;

    ReviewTag(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
} 