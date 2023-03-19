package com.frod.core.client.naver;

import com.frod.core.client.naver.base.NaverWebClient;
import com.frod.core.client.naver.constant.NaverApiInfo;
import com.frod.core.client.naver.dto.request.NaverBlogSearchRequest;
import com.frod.core.client.naver.dto.response.NaverBlogSearchResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class NaverBlogWebClient {
    private final NaverWebClient naverWebClient;
    public Mono<NaverBlogSearchResponse> search(NaverBlogSearchRequest request) {
        WebClient.RequestBodySpec webClient = naverWebClient.client()
                .method(NaverApiInfo.NAVER_BLOG_SEARCH_JSON_GET.getMethod())
                .uri(uriBuilder -> uriBuilder
                        .path(NaverApiInfo.NAVER_BLOG_SEARCH_JSON_GET.getPath())
                        .queryParam("query",request.getQuery())
                        .queryParam("sort",request.getSort())
                        .queryParam("display",request.getDisplay())
                        .queryParam("start",request.getStart())
                        .build());

        return naverWebClient.exchanged(webClient, new ParameterizedTypeReference<NaverBlogSearchResponse>() {
        });
    }
}
