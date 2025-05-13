package com.example.taggo.domain.place.repository;

import com.example.taggo.domain.place.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    Optional<Place> findByKakaoId(Long kakaoId);
}
