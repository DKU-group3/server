package com.example.taggo.domain.tag.api.controller;

import com.example.taggo.domain.tag.api.response.TagListResponse;
import com.example.taggo.domain.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/tags")
public class TagController {

    private final TagService tagService;

    @GetMapping
    public ResponseEntity<TagListResponse> getAllTags(){
        return ResponseEntity.ok(tagService.findAll());
    }
}
