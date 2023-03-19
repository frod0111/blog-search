package com.frod.core.client.naver.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;

@Getter
@RequiredArgsConstructor
public enum NaverApiInfo {
    NAVER_BLOG_SEARCH_JSON_GET("/v1/search/blog.json","네이버 블로그 검색 (JSON)",HttpMethod.GET);

    private final String path;
    private final String title;
    private final HttpMethod method;
}
