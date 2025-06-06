package com.example.taggo.domain.review.model;

import com.example.taggo.common.model.BaseEntity;
import com.example.taggo.domain.image.model.Image;
import com.example.taggo.domain.place.model.Place;
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

    @Column(nullable = false, length = 2000)
    private String content;

    @OneToMany(mappedBy = "review", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id", nullable = false, updatable = false)
    private Place place;

    public static Review create(Place place, Double rating, String content) {
        return Review.builder()
                .rating(rating)
                .content(content)
                .images(new ArrayList<>())
                .place(place)
                .build();
    }
}
