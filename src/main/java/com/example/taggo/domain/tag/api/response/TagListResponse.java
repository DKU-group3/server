package com.example.taggo.domain.tag.api.response;

import com.example.taggo.domain.tag.model.Tag;

import java.util.List;

public record TagListResponse(
    List<String> tags
) {
    public static TagListResponse from(List<Tag> tags) {
        List<String> lists = tags.stream().map(Tag::getName).toList();

        return new TagListResponse(lists);
    }
}
