package com.example.taggo.domain.search.service;

import com.example.taggo.domain.place.service.PlaceService;
import com.example.taggo.domain.search.api.response.SearchResultListResponse;
import com.example.taggo.domain.search.api.response.SearchResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchApiService {

    private final PlaceService placeService;

    @Value("${KAKAO_APP_KEY}")
    private String key;
    private String url = "https://dapi.kakao.com/v2/local/search/keyword.json";
    private final RestTemplate restTemplate = new RestTemplate();

    public SearchResultListResponse keywordSearchWithTags(String query, List<String> tagNames) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "KakaoAK " + key);
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        List<SearchResultResponse> allResults = new java.util.ArrayList<>();

        for (int page = 1; page <= 3; page++) {
            URI targetUri = UriComponentsBuilder
                    .fromUriString(url)
                    .queryParam("query", query)
                    .queryParam("page", page)
                    .queryParam("size", 15)
                    .build()
                    .encode(StandardCharsets.UTF_8)
                    .toUri();

            ResponseEntity<SearchResultListResponse> result = restTemplate.exchange(
                    targetUri, HttpMethod.GET, httpEntity, SearchResultListResponse.class
            );

            SearchResultListResponse response = result.getBody();
            if (response != null && response.result() != null) {
                allResults.addAll(response.result());
            }
        }

        if (tagNames == null || tagNames.isEmpty()) {
            return new SearchResultListResponse(allResults);
        }

        List<SearchResultResponse> filtered = allResults.stream()
                .filter(doc -> placeService.findByNameOptional(doc.placeName())
                        .map(place -> place.getTags().stream()
                                .map(placeTag -> placeTag.getTag().getName())
                                .collect(Collectors.toSet())
                                .containsAll(tagNames)
                        ).orElse(false))
                .collect(Collectors.toList());

        return new SearchResultListResponse(filtered);
    }

}
