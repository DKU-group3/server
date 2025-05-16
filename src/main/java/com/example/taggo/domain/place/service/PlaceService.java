package com.example.taggo.domain.place.service;

import com.example.taggo.domain.place.model.Place;
import com.example.taggo.domain.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    public Place findOrCreate(Long kakaoId, String name){
        return placeRepository.findByKakaoId(kakaoId).orElseGet(() ->
                placeRepository.save(Place.create(kakaoId, name)));
    }
}
