package com.example.taggo.domain.tag.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@Table(name = "Tag")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class Tag {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(unique = true)
    private String tagName;

}
