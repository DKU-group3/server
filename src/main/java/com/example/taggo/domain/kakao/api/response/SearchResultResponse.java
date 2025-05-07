package com.example.taggo.domain.kakao.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SearchResultResponse(

        @JsonProperty("id")
        String id,

        @JsonProperty("place_name")
        String placeName,

        @JsonProperty("address_name")
        String addressName,

        @JsonProperty("road_address_name")
        String roadAddress,

        @JsonProperty("phone")
        String phone,

        @JsonProperty("place_url")
        String placeUrl,

        @JsonProperty("x")
        String longitude,

        @JsonProperty("y")
        String latitude
) {
}
