package com.example.taggo.domain.placetag.repository;

import com.example.taggo.domain.place.model.Place;
import com.example.taggo.domain.placetag.model.PlaceTag;
import com.example.taggo.domain.tag.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceTagRepository extends JpaRepository<PlaceTag, Long> {
    boolean existsByPlaceAndTag(Place place, Tag tag);
}
