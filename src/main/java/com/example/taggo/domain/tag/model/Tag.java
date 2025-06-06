package com.example.taggo.domain.tag.model;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@Table(name = "Tag")
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class Tag {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    public static Tag create(String name) {
        return Tag.builder()
                .name(name)
                .build();
    }
}
