package com.example.taggo.domain.placetag.repository;

import com.example.taggo.domain.placetag.model.PlaceTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceTagRepository extends JpaRepository<PlaceTag, Long> {
}
