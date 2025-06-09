package com.example.server5.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class ReviewAnalysisService {

    // 태그 카테고리별 키워드 정의
    private static final Map<String, List<String>> TAG_KEYWORDS = new HashMap<>();
    private static final List<String> NEGATIVE_KEYWORDS = Arrays.asList(
        "별로", "실망", "불친절", "비싸", "맛없", "기다림", "불만", "불편", "불량", "불량품", "불량식품",
        "불량식당", "불량식당", "불량식당", "불량식당", "불량식당", "불량식당", "불량식당", "불량식당"
    );

    // 태그별 이모티콘 매핑
    private static final Map<String, String> TAG_EMOJIS = Map.of(
        "맛", "good",
        "가격", "good",
        "서비스", "good",
        "분위기", "good",
        "양", "good"
    );

    static {
        // 맛 관련 키워드
        TAG_KEYWORDS.put("맛", Arrays.asList(
            "맛있", "맛나", "맛좋", "맛집", "맛난", "맛있는", "맛좋은", "맛난", "맛있는", "맛좋은",
            "맛난", "맛있는", "맛좋은", "맛난", "맛있는", "맛좋은", "맛난", "맛있는", "맛좋은"
        ));

        // 가격 관련 키워드
        TAG_KEYWORDS.put("가격", Arrays.asList(
            "가성비", "저렴", "합리", "착한", "적당", "괜찮", "좋은", "만족", "가치", "가치있는"
        ));

        // 서비스 관련 키워드
        TAG_KEYWORDS.put("서비스", Arrays.asList(
            "친절", "서비스", "배려", "관심", "신경", "도움", "편안", "편리", "편의", "편의시설"
        ));

        // 분위기 관련 키워드
        TAG_KEYWORDS.put("분위기", Arrays.asList(
            "분위기", "인테리어", "깔끔", "청결", "위생", "깨끗", "정돈", "정리", "정돈", "정리"
        ));

        // 양 관련 키워드
        TAG_KEYWORDS.put("양", Arrays.asList(
            "양", "양이", "양이많", "양이적", "양이적당", "양이많은", "양이적당한", "양이많은", "양이적당한"
        ));
    }

    public Map<String, Object> analyzeRestaurant(Map<String, Object> restaurant) {
        Map<String, Object> result = new HashMap<>();
        result.put("storeName", restaurant.get("storeName"));
        result.put("category", restaurant.get("category"));
        
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> reviews = (List<Map<String, Object>>) restaurant.get("reviews");
        
        if (reviews == null || reviews.isEmpty()) {
            result.put("tags", Collections.emptyList());
            return result;
        }

        // 모든 리뷰를 분석하되, 부정적인 키워드가 포함된 문장은 제외
        List<String> validReviews = reviews.stream()
            .map(review -> (String) review.get("comment"))
            .filter(comment -> comment != null && !comment.trim().isEmpty())
            .filter(comment -> !containsNegativeKeywords(comment))
            .collect(Collectors.toList());

        // 태그 카운트 맵
        Map<String, Integer> tagCounts = new HashMap<>();

        // 각 카테고리별 키워드 매칭 및 카운트
        for (String review : validReviews) {
            for (Map.Entry<String, List<String>> entry : TAG_KEYWORDS.entrySet()) {
                String category = entry.getKey();
                List<String> keywords = entry.getValue();

                for (String keyword : keywords) {
                    if (review.contains(keyword)) {
                        tagCounts.merge(category, 1, Integer::sum);
                    }
                }
            }
        }

        // 상위 3개 태그 선택
        List<String> topTags = tagCounts.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .limit(3)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());

        // 태그명 옆에 바로 이모티콘(여기서는 'good')을 붙여서 반환
        List<String> emojiTags = topTags.stream()
            .map(tag -> tag + TAG_EMOJIS.getOrDefault(tag, ""))
            .collect(Collectors.toList());
        result.put("tags", emojiTags);
        return result;
    }

    public List<Map<String, Object>> analyzeRestaurants(List<Map<String, Object>> restaurants) {
        return restaurants.stream()
            .map(this::analyzeRestaurant)
            .collect(Collectors.toList());
    }

    private boolean containsNegativeKeywords(String text) {
        return NEGATIVE_KEYWORDS.stream().anyMatch(text::contains);
    }
} 