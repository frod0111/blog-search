package com.frod.core.service;

import com.frod.core.client.kakao.KakaoBlogWebClient;
import com.frod.core.client.kakao.dto.request.KakaoBlogSearchRequest;
import com.frod.core.client.kakao.dto.response.KaKaoBlogSearchResponse;
import com.frod.core.common.constant.CustomExceptionType;
import com.frod.core.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
public interface KaKaoSearchService {
    KaKaoBlogSearchResponse kakaoBlogSearch(KakaoBlogSearchRequest request);
}
