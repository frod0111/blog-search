package com.frod.core.service;

import com.frod.core.client.naver.NaverBlogWebClient;
import com.frod.core.client.naver.dto.request.NaverBlogSearchRequest;
import com.frod.core.client.naver.dto.response.NaverBlogSearchResponse;
import com.frod.core.common.constant.CustomExceptionType;
import com.frod.core.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
public interface NaverSearchService {
    NaverBlogSearchResponse naverBlogSearch(NaverBlogSearchRequest request);
}
