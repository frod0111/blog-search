package com.frod.api.service;

import com.frod.api.constant.ApiType;
import com.frod.api.dto.request.BlogSearchRequest;
import com.frod.api.dto.response.BlogSearchResponse;
import com.frod.api.mapper.KakaoBlogSearchMapper;
import com.frod.api.mapper.NaverBlogSearchMapper;
import com.frod.core.client.kakao.dto.request.KakaoBlogSearchRequest;
import com.frod.core.client.kakao.dto.response.KaKaoBlogSearchResponse;
import com.frod.core.client.naver.dto.request.NaverBlogSearchRequest;
import com.frod.core.client.naver.dto.response.NaverBlogSearchResponse;
import com.frod.core.common.constant.CustomExceptionType;
import com.frod.core.common.exception.CustomException;
import com.frod.core.dto.cmd.KeywordCmd;
import com.frod.core.dto.query.KeywordQuery;
import com.frod.core.entity.KeyWordEntity;
import com.frod.core.repository.KeywordRepository;
import com.frod.core.service.KaKaoSearchService;
import com.frod.core.service.KeywordService;
import com.frod.core.service.NaverSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchService {

    private final KakaoBlogSearchMapper kakaoBlogSearchMapper;
    private final NaverBlogSearchMapper naverBlogSearchMapper;
    private final NaverSearchService naverSearchService;
    private final KaKaoSearchService kaKaoSearchService;
    private final KeywordService keywordService;

    public BlogSearchResponse blogSearch(BlogSearchRequest request) {
        if(!ObjectUtils.isEmpty(request)) {

            if(keywordService.exitsValid(request.getKeyword())) {
                keywordService.updateCount(KeywordCmd.builder().keyword(request.getKeyword()).build());
            } else {
                keywordService.save(KeywordCmd.builder().keyword(request.getKeyword()).count(1).insertDate(LocalDateTime.now()).updateDate(LocalDateTime.now()).build());
            }

            //네이버 블로그 검색 조회 시도
            if(ApiType.NAVER.equals(request.getApiType())) {
                try {
                    var naverResult = this.naverBlogSearch(naverBlogSearchMapper.toNaverBlogSearchRequest(request));
                    return naverBlogSearchMapper.toBlogSearchResponse(naverResult,request.getPageNo(),request.getPageSize());
                } catch (Exception e) {
                    throw new CustomException(CustomExceptionType.API_ERROR);
                }
            }

            //카카오 블로그 검색 조회 시도
            try {
                var kakaoResult = this.kakaoBlogSearch(kakaoBlogSearchMapper.toKakaoBlogSearchRequest(request));
                return kakaoBlogSearchMapper.toBlogSearchResponse(kakaoResult);
            } catch (Exception e) {
                try {
                    //네이버 블로그 검색 조회 시도
                    var naverResult = this.naverBlogSearch(naverBlogSearchMapper.toNaverBlogSearchRequest(request));
                    return naverBlogSearchMapper.toBlogSearchResponse(naverResult,request.getPageNo(),request.getPageSize());
                } catch (Exception ex) {
                    throw new CustomException(CustomExceptionType.API_ERROR);
                }
            }



        } else {
            throw new CustomException(CustomExceptionType.INVALID_PARAMETER);
        }
    }

    public KaKaoBlogSearchResponse kakaoBlogSearch(KakaoBlogSearchRequest request) {
        log.info("KAKAO BLOG SEARH START !!");
        return kaKaoSearchService.kakaoBlogSearch(request);
    }

    public NaverBlogSearchResponse naverBlogSearch(NaverBlogSearchRequest request) {
        log.info("NAVER BLOG SEARH START !!");
        return naverSearchService.naverBlogSearch(request);
    }

    public List<KeywordQuery> rankKeywordSearch() {
        return keywordService.top10SearchedList();
    }
}
