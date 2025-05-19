package com.example.taggo.domain.tag.service;

import com.example.taggo.domain.tag.model.Tag;
import com.example.taggo.domain.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public List<Tag> findAllByIds(List<Long> ids) {
        return tagRepository.findAllById(ids);
    }
}
