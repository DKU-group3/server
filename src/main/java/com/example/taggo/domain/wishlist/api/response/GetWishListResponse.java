package com.example.taggo.domain.wishlist.api.response;

import com.example.taggo.domain.wishlist.model.WishList;

import java.util.List;

public record GetWishListResponse(
        List<WishListResponse> result
) {
    public static GetWishListResponse from(List<WishList> wishList) {
        List<WishListResponse> result = wishList.stream()
                .map(WishListResponse::from)
                .toList();
        return new GetWishListResponse(result);
    }
}
