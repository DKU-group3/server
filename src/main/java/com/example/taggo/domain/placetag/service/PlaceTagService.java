package com.example.taggo.domain.placetag.service;

import com.example.taggo.domain.place.model.Place;
import com.example.taggo.domain.placetag.model.PlaceTag;
import com.example.taggo.domain.tag.model.Tag;
import com.example.taggo.domain.placetag.repository.PlaceTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlaceTagService {

    private final PlaceTagRepository placeTagRepository;

    @Transactional
    public void link(Place place, Tag tag) {
        if (!placeTagRepository.existsByPlaceAndTag(place, tag)) {
            placeTagRepository.save(PlaceTag.create(place, tag));
        }
    }

}
