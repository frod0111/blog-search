package com.frod.api.service;

import com.frod.api.dto.request.BlogSearchRequest;
import com.frod.api.dto.response.BlogSearchResponse;
import com.frod.api.mapper.KakaoBlogSearchMapper;
import com.frod.core.service.KaKaoSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchService {

    private final KakaoBlogSearchMapper kakaoBlogSearchMapper;

    private final KaKaoSearchService kaKaoSearchService;
    public BlogSearchResponse blogSearch(BlogSearchRequest request) {
        var kakaoSearchRequest = kakaoBlogSearchMapper.toKakaoBlogSearchRequest(request);
        var kakaoSearch = kaKaoSearchService.kakaoBlogSearch(kakaoSearchRequest);
        var response = kakaoBlogSearchMapper.toBlogSearchResponse(kakaoSearch);
        return response;
    }
}
