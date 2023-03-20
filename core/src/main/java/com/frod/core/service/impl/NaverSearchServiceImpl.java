package com.frod.core.service.impl;

import com.frod.core.client.naver.NaverBlogWebClient;
import com.frod.core.client.naver.dto.request.NaverBlogSearchRequest;
import com.frod.core.client.naver.dto.response.NaverBlogSearchResponse;
import com.frod.core.common.constant.CustomExceptionType;
import com.frod.core.common.exception.CustomException;
import com.frod.core.service.NaverSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NaverSearchServiceImpl implements NaverSearchService {
    private final NaverBlogWebClient naverBlogWebClient;

    @Override
    public NaverBlogSearchResponse naverBlogSearch(NaverBlogSearchRequest request) {
        return naverBlogWebClient.search(request)
                .flux()
                .toStream()
                .findFirst()
                .orElseThrow(() -> new CustomException(CustomExceptionType.API_CLIENT_NOT_FOUND));
    }
}
