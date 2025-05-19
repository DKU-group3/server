package com.example.taggo.domain.reviewtag.model;

import com.example.taggo.domain.review.model.Review;
import com.example.taggo.domain.tag.model.Tag;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Getter
@Entity
@Table(name = "ReviewTag")
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class ReviewTag {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false, updatable = false)
    private Review review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id", nullable = false, updatable = false)
    private Tag tag;

    public static ReviewTag create(Review review, Tag tag) {
        return ReviewTag.builder()
                .review(review)
                .tag(tag)
                .build();
    }
}
