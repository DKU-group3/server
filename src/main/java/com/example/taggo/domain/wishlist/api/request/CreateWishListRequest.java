package com.example.taggo.domain.wishlist.api.request;

import jakarta.validation.constraints.NotNull;

public record CreateWishListRequest(

        @NotNull
        String placeName
) {
}
