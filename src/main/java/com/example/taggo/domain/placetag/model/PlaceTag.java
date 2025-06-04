package com.example.taggo.domain.placetag.model;

import com.example.taggo.domain.place.model.Place;
import com.example.taggo.domain.tag.model.Tag;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class PlaceTag {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id", nullable = false, updatable = false)
    private Place place;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id", nullable = false, updatable = false)
    private Tag tag;

    public static PlaceTag create(Place place, Tag tag) {
        return builder()
                .place(place)
                .tag(tag)
                .build();
    }

}
