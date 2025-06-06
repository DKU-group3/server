package com.example.taggo.domain.image.model;

import com.example.taggo.common.model.BaseEntity;
import com.example.taggo.domain.review.model.Review;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.*;

@Entity
@Table(name = "images")
@Builder
@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class Image extends BaseEntity {
    @Id  @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false, updatable = false)
    private Review review;

    public static Image create(String imageUrl, Review review) {
        return Image.builder()
                .imageUrl(imageUrl)
                .review(review)
                .build();
    }
}
