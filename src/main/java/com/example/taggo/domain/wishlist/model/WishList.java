package com.example.taggo.domain.wishlist.model;

import com.example.taggo.domain.common.model.BaseEntity;
import com.example.taggo.domain.place.model.Place;
import com.example.taggo.domain.user.model.User;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.*;

@Entity
@Getter
@Builder
@Table(name = "WishList")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class WishList extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id", nullable = false, updatable = false)
    private Place place;

    public static WishList create(User user, Place place) {
        return WishList.builder()
                .user(user)
                .place(place)
                .build();
    }
}
