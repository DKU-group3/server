package com.example.taggo.domain.review.model;

import com.example.taggo.domain.common.model.BaseEntity;
import com.example.taggo.domain.place.model.Place;
import com.example.taggo.domain.reviewtag.model.ReviewTag;
import com.example.taggo.domain.tag.model.Tag;
import com.example.taggo.domain.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@Table(name = "review")
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class Review extends BaseEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private Double rating;

    private String content;

    private String youtubeUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id", nullable = false, updatable = false)
    private Place place;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewTag> tags = new ArrayList<>();

    public static Review create(User user, Place place, Double rating ,String content, String youtubeUrl, List<Tag> tags) {
        Review review = Review.builder()
                .user(user)
                .place(place)
                .rating(rating)
                .content(content)
                .youtubeUrl(youtubeUrl)
                .tags(new ArrayList<>())
                .build();

        for (Tag tag : tags) {
            ReviewTag reviewTag = ReviewTag.create(review, tag);
            review.getTags().add(reviewTag);
        }

        return review;
    }
}
