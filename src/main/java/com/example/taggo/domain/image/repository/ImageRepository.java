package com.example.taggo.domain.image.repository;

import com.example.taggo.domain.image.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
