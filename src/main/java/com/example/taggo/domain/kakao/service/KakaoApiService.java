package com.example.taggo.domain.kakao.service;

import com.example.taggo.domain.kakao.api.response.SearchResultListResponse;
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

@Service
@RequiredArgsConstructor
public class KakaoApiService {

    @Value("${KAKAO_APP_KEY}")
    private String key;
    private String url = "https://dapi.kakao.com/v2/local/search/keyword.json";
    private final RestTemplate restTemplate = new RestTemplate();

    public SearchResultListResponse keywordSearch(String query) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "KakaoAK " + key);
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        URI targetUri = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("query", query)
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUri();

        ResponseEntity<SearchResultListResponse> result = restTemplate.exchange(targetUri, HttpMethod.GET, httpEntity, SearchResultListResponse.class);
        return result.getBody();
    }
}
