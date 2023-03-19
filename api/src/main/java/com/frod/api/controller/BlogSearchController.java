package com.frod.api.controller;

import com.frod.api.dto.request.BlogSearchRequest;
import com.frod.api.dto.response.BlogSearchResponse;
import com.frod.api.service.SearchService;
import com.frod.core.client.kakao.KakaoBlogWebClient;
import com.frod.core.client.kakao.dto.request.KakaoBlogSearchRequest;
import com.frod.core.client.kakao.dto.response.KaKaoBlogSearchResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/blog-search")
@RequiredArgsConstructor
public class BlogSearchController {

    private final SearchService searchService;
    @GetMapping
    @ApiOperation(value = "블로그 검색 API",notes = "블로그를 검색합니다.")
    public ResponseEntity<BlogSearchResponse> search(@Valid BlogSearchRequest request) {
        var search = searchService.blogSearch(request);
        return ResponseEntity.ok(search);
    }

    @GetMapping("/test")
    @ApiOperation(value = "블로그 검색 API",notes = "블로그를 검색합니다.")
    public ResponseEntity<String> test(KakaoBlogSearchRequest request) {
        return ResponseEntity.ok("OK");
    }
}
