package com.example.taggo.domain.place.model;

import com.example.taggo.common.model.BaseEntity;
import com.example.taggo.domain.placetag.model.PlaceTag;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "place")
public class Place extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = true, length = 1000)
    private String imageUrl;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlaceTag> tags = new ArrayList<>();

    public static Place create(String name, String imageUrl) {
        return Place.builder()
                .name(name)
                .imageUrl(imageUrl)
                .tags(new ArrayList<>())
                .build();
    }
}
