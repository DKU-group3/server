package com.example.server5.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ReviewAnalysisRunner {
    public static void main(String[] args) throws Exception {
        // JSON 파일을 읽어서 문자열로 변환 (input.json 파일 필요)
        String json = new String(Files.readAllBytes(Paths.get("input.json")), "UTF-8");

        // JSON을 List<Map<String, Object>>로 변환
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> restaurantList = mapper.readValue(json, List.class);

        // 서비스 호출
        ReviewAnalysisService service = new ReviewAnalysisService();
        List<Map<String, Object>> result = service.analyzeRestaurants(restaurantList);

        // 결과 출력
        for (Map<String, Object> store : result) {
            System.out.println(store.get("storeName") + " (" + store.get("category") + ") -> 태그: " + store.get("tags"));
        }
    }
} 