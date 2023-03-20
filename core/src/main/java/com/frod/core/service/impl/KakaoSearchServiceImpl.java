package com.frod.core.service.impl;

import com.frod.core.client.kakao.KakaoBlogWebClient;
import com.frod.core.client.kakao.dto.request.KakaoBlogSearchRequest;
import com.frod.core.client.kakao.dto.response.KaKaoBlogSearchResponse;
import com.frod.core.common.constant.CustomExceptionType;
import com.frod.core.common.exception.CustomException;
import com.frod.core.service.KaKaoSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoSearchServiceImpl implements KaKaoSearchService {
    private final KakaoBlogWebClient kakaoBlogWebClient;
    @Override
    public KaKaoBlogSearchResponse kakaoBlogSearch(KakaoBlogSearchRequest request) {
        return kakaoBlogWebClient.search(request)
                .flux()
                .toStream()
                .findFirst()
                .orElseThrow(() -> new CustomException(CustomExceptionType.API_CLIENT_NOT_FOUND));
    }
}
