package com.frod.core.service;

import com.frod.core.client.naver.NaverBlogWebClient;
import com.frod.core.client.naver.dto.request.NaverBlogSearchRequest;
import com.frod.core.client.naver.dto.response.NaverBlogSearchResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NaverSearchService {
    private final NaverBlogWebClient naverBlogWebClient;

    public NaverBlogSearchResponse naverBlogSearch(NaverBlogSearchRequest request) {
        return naverBlogWebClient.search(request)
                .flux()
                .toStream()
                .findFirst()
                .get();
    }
}
