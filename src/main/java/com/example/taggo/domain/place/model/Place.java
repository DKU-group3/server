package com.example.taggo.domain.place.model;

import com.example.taggo.domain.common.model.BaseEntity;
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

    @Column(unique = true)
    private Long kakaoId;

    private String name;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlaceTag> tags = new ArrayList<>();

    public static Place create(Long kakaoId, String name){
        return Place.builder()
                .kakaoId(kakaoId)
                .name(name)
                .tags(new ArrayList<>())
                .build();
    }
}
