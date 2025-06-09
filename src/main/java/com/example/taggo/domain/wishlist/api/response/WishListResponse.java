package com.example.taggo.domain.wishlist.api.response;

import com.example.taggo.domain.wishlist.model.WishList;

public record WishListResponse(
        Long id,
        String placeName,
        String imgUrl
) {
    public static WishListResponse from(WishList wishList) {
        return new WishListResponse(
                wishList.getId(),
                wishList.getPlace().getName(),
                wishList.getPlace().getImageUrl()
        );
    }
}
