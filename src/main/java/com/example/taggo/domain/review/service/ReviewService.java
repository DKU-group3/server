package com.example.taggo.domain.review.service;

import com.example.taggo.domain.place.model.Place;
import com.example.taggo.domain.place.service.PlaceService;
import com.example.taggo.domain.placetag.model.PlaceTag;
import com.example.taggo.domain.review.api.request.AddReviewRequest;
import com.example.taggo.domain.review.api.response.ReviewListResponse;
import com.example.taggo.domain.review.model.Review;
import com.example.taggo.domain.review.repository.ReviewRepository;
import com.example.taggo.domain.tag.model.Tag;
import com.example.taggo.domain.tag.service.TagService;
import com.example.taggo.domain.user.model.User;
import com.example.taggo.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final PlaceService placeService;
    private final UserService userService;
    private final TagService tagService;

    @Transactional(readOnly = true)
    public ReviewListResponse findByUserId(){
        User user = userService.getById(1L);
        return ReviewListResponse.from(reviewRepository.findByUser(user));
    }

    @Transactional(readOnly = true)
    public ReviewListResponse findByKakaoId(Long KakaoId){
        return ReviewListResponse.from(reviewRepository.findByPlace(
                placeService.findByKaKaoId(KakaoId))
        );
    }

    public void create(AddReviewRequest request) {
        // To-do SecurityContext 에서 유저 아이디 가져오기
        User user = userService.getById(1L);
        Place place = placeService.findOrCreate(request.kakaoId(), request.placeName());

        List<Tag> tags = new ArrayList<>();

        if (request.tagIds() != null && !request.tagIds().isEmpty()) {
            tags = tagService.findAllByIds(request.tagIds());
        }

        //Tag 와 Place 연관
        for(Tag tag : tags) {
            if(place.getTags().stream().noneMatch(pt -> pt.getTag().equals(tag))) {
                place.getTags().add(PlaceTag.create(place, tag));
            }
        }

        // 리뷰 생성 내부에 리뷰와 태그 연관
        reviewRepository.save(Review.create(
                user, place, request.rating(), request.content(), request.youtubeUrl(), tags
        ));
    }

}
