package com.example.taggo.domain.reviewtag.repository;

import com.example.taggo.domain.reviewtag.model.ReviewTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewTagRepository extends JpaRepository<ReviewTag, Long> {
}
