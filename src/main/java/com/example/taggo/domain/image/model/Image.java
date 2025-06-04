package com.example.taggo.domain.image.model;

import com.example.taggo.common.model.BaseEntity;
import com.example.taggo.domain.review.model.Review;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.*;

@Entity
@Table(name = "images")
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Image extends BaseEntity {
    @Id  @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false, updatable = false)
    private Review review;
}
