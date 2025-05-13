package com.example.taggo.domain.place.model;

import com.example.taggo.domain.common.model.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

//    private String address;
//
//    @Column(unique = true)
//    private String phone;

    public static Place create(Long kakaoId, String name){
        return Place.builder()
                .kakaoId(kakaoId)
                .name(name)
                .build();
    }
}
