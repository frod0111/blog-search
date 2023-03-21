package com.frod.api;

import com.frod.core.client.kakao.constant.KakaoSortType;
import com.frod.core.client.kakao.dto.request.KakaoBlogSearchRequest;
import com.frod.core.client.naver.constant.NaverSortType;
import com.frod.core.client.naver.dto.request.NaverBlogSearchRequest;
import com.frod.core.common.exception.ExternalApiException;
import com.frod.core.service.impl.KakaoSearchServiceImpl;
import com.frod.core.service.impl.NaverSearchServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
public class SearchServiceTest {
    @Autowired
    private NaverSearchServiceImpl naverSearchService;
    @Autowired
    private KakaoSearchServiceImpl kaKaoSearchService;
    @Test
    @DisplayName("네이버_블로그검색_서비스_테스트")
    public void naverSearchTest() {
        //given
        var request = NaverBlogSearchRequest.builder()
                .display(10)
                .sort("sim")
                .start(1)
                .query("라라랜드")
                .build();
        var result = naverSearchService.naverBlogSearch(request);
        //then
        Assertions.assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("네이버_블로그검색_서비스_예외_테스트")
    public void naverSearchExceptinTest() {
        //given
        var request = NaverBlogSearchRequest.builder()
                .display(10)
                .sort(NaverSortType.SIM.getName())
                .start(0)
                .query("라라랜드")
                .build();

        //when & then
        assertThatThrownBy(() -> naverSearchService.naverBlogSearch(request))
                .isInstanceOf(ExternalApiException.class);
    }

    @Test
    @DisplayName("카카오_블로그검색_서비스_테스트")
    public void kakaoSearchTest() {
        //given
        var kakaoRequest = KakaoBlogSearchRequest.builder()
                .query("라라랜드")
                .sort(KakaoSortType.ACCURACY.getName())
                .page(1)
                .size(10)
                .build();
        var result = kaKaoSearchService.kakaoBlogSearch(kakaoRequest);
        //then
        Assertions.assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("카카오_블로그검색_서비스_예외_테스트")
    public void kakaoSearchExceptinTest() {
        //given
        var kakaoRequest = KakaoBlogSearchRequest.builder()
                .query("라라랜드")
                .sort(KakaoSortType.ACCURACY.getName())
                .page(0)
                .size(10)
                .build();

        //when & then
        assertThatThrownBy(() -> kaKaoSearchService.kakaoBlogSearch(kakaoRequest))
                .isInstanceOf(ExternalApiException.class);
    }
}