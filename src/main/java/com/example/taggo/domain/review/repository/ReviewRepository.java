package com.example.taggo.domain.review.repository;

import com.example.taggo.domain.place.model.Place;
import com.example.taggo.domain.review.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByPlace(Place place);

}
