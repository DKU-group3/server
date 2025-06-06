package com.example.taggo.domain.place.service;

import com.example.taggo.common.exception.BaseException;
import com.example.taggo.common.exception.ErrorType;
import com.example.taggo.domain.place.model.Place;
import com.example.taggo.domain.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.taggo.common.exception.ErrorType.*;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    public Place findByName(String name) {
        return placeRepository.findByName(name)
                .orElseThrow(() -> new BaseException(NOTFOUND_PLACE));
    }

    public Optional<Place> findByNameOptional(String name) {
        return placeRepository.findByName(name);
    }

    public Place findOrCreate(String name){
        return placeRepository.findByName(name).orElseGet(() ->
                placeRepository.save(Place.create(name, null)));
    }

    public Place createOrGet(String name, String imageUrl) {
        return placeRepository.findByName(name)
                .orElseGet(() -> placeRepository.save(
                        Place.create(
                                name,
                                imageUrl
                        )
                ));
    }

}
