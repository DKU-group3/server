package com.example.taggo.domain.wishlist.repository;

import com.example.taggo.domain.wishlist.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishListRepository extends JpaRepository<WishList, Long> {
    List<WishList> findByUserId(Long userId);
}
