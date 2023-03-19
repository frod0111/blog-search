package com.frod.core.client.kakao.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;

@Getter
@RequiredArgsConstructor
public enum KakaoApiInfo {

    BLOG_SEARCH_GET("/v2/search/blog", "카카오 블로그 검색", HttpMethod.GET);

    private final String path;
    private final String title;
    private final HttpMethod method;
}
