package com.frod.core.service;

import com.frod.core.client.kakao.KakaoBlogWebClient;
import com.frod.core.client.kakao.dto.request.KakaoBlogSearchRequest;
import com.frod.core.client.kakao.dto.response.KaKaoBlogSearchResponse;
import com.frod.core.common.constant.CustomExceptionType;
import com.frod.core.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KaKaoSearchService {

    private final KakaoBlogWebClient kakaoBlogWebClient;

    public KaKaoBlogSearchResponse kakaoBlogSearch(KakaoBlogSearchRequest request) {
        return kakaoBlogWebClient.search(request)
                .flux()
                .toStream()
                .findFirst()
                .orElseThrow(() -> new CustomException(CustomExceptionType.API_CLIENT_NOT_FOUND));
    }
}
