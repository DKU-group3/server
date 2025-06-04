package com.example.taggo.domain.tag.repository;

import com.example.taggo.domain.tag.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
