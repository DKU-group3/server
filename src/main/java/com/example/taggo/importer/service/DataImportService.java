package com.example.taggo.importer.service;

import com.example.taggo.domain.image.service.ImageService;
import com.example.taggo.domain.place.model.Place;
import com.example.taggo.domain.place.service.PlaceService;
import com.example.taggo.domain.placetag.service.PlaceTagService;
import com.example.taggo.domain.review.model.Review;
import com.example.taggo.domain.review.service.ReviewService;
import com.example.taggo.domain.tag.model.Tag;
import com.example.taggo.domain.tag.service.TagService;
import com.example.taggo.importer.request.PlaceImportRequest;
import com.example.taggo.importer.request.ReviewImportRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DataImportService {

    private final PlaceService placeService;
    private final ReviewService reviewService;
    private final TagService tagService;
    private final PlaceTagService placeTagService;
    private final ImageService imageService;

    @Transactional
    public void importData(List<PlaceImportRequest> requests) {
        for (PlaceImportRequest dto : requests) {
            Place place = placeService.createOrGet(dto.storeName(), dto.storeImg());

            // 2. 태그 저장 및 PlaceTag 생성
            for (String tagName : dto.tags()) {
                Tag tag = tagService.createOrGet(tagName);

                placeTagService.link(place, tag);
            }

            // 3. 리뷰 저장 및 이미지 연결
            for (ReviewImportRequest reviewDto : dto.reviews()) {
                Review review = reviewService.create(place, reviewDto.rating(), reviewDto.comment());
                if (reviewDto.imgUrl() != null) {
                    imageService.create(reviewDto.imgUrl(), review);
                }
            }
        }
    }
}
