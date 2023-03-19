package com.frod.core.client.kakao;

import com.frod.core.client.kakao.base.KakaoWebClient;
import com.frod.core.client.kakao.constant.KakaoApiInfo;
import com.frod.core.client.kakao.dto.request.KakaoBlogSearchRequest;
import com.frod.core.client.kakao.dto.response.KaKaoBlogSearchResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoBlogWebClient {
    private final KakaoWebClient kakaoWebClient;

    public Mono<KaKaoBlogSearchResponse> search(KakaoBlogSearchRequest request) {
        WebClient.RequestBodySpec webClient = kakaoWebClient
                .client()
                .method(KakaoApiInfo.BLOG_SEARCH_GET.getMethod())
                .uri(uriBuilder -> uriBuilder.path(KakaoApiInfo.BLOG_SEARCH_GET.getPath())
                        .queryParam("query",request.getQuery())
                        .queryParam("sort",request.getSort())
                        .queryParam("page",request.getPage())
                        .queryParam("size",request.getSize())
                        .build());
        return kakaoWebClient.exchanged(webClient, new ParameterizedTypeReference<KaKaoBlogSearchResponse>() {
        });
    }
}
