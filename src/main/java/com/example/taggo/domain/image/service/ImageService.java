package com.example.taggo.domain.image.service;

import com.example.taggo.domain.image.model.Image;
import com.example.taggo.domain.image.repository.ImageRepository;
import com.example.taggo.domain.review.model.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;


    public void create(String imageUrl, Review review) {
        imageRepository.save(Image.create(imageUrl, review));
    }

}
