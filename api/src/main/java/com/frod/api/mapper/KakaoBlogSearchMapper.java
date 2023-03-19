package com.frod.api.mapper;

import com.frod.api.constant.SortType;
import com.frod.api.dto.request.BlogSearchRequest;
import com.frod.api.dto.response.BlogSearchResponse;
import com.frod.api.dto.response.ItemResponse;
import com.frod.core.client.kakao.constant.KakaoSortType;
import com.frod.core.client.kakao.dto.request.KakaoBlogSearchRequest;
import com.frod.core.client.kakao.dto.response.DocumentsResponse;
import com.frod.core.client.kakao.dto.response.KaKaoBlogSearchResponse;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE

)
public interface KakaoBlogSearchMapper {
    @Mapping(target = "query", source = "request.keyword")
    @Mapping(target = "sort",  expression = "java(toKakaoSortType(request.getSortType()))")
    @Mapping(target = "page",  source = "request.pageNo")
    @Mapping(target = "size",  source = "request.pageSize")
    KakaoBlogSearchRequest toKakaoBlogSearchRequest(BlogSearchRequest request);

    @Mapping(target = "title", source = "kakaoDocument.title")
    @Mapping(target = "contents", source = "kakaoDocument.contents")
    @Mapping(target = "url", source = "kakaoDocument.url")
    @Mapping(target = "blogName", source = "kakaoDocument.blogname")
    @Mapping(target = "thumbnail", source = "kakaoDocument.thumbnail")
    @Mapping(target = "postedDate", source = "kakaoDocument.datetime")
    ItemResponse toItemResponse(DocumentsResponse kakaoDocument);

    @Mapping(target = "totalCount", source = "response.meta.pageable_count")
    @Mapping(target = "isEnd", source = "response.meta.is_end")
    @Mapping(target = "apiType", constant = "KAKAO")
    @Mapping(target = "items", source = "documents")
    BlogSearchResponse toBlogSearchResponse(KaKaoBlogSearchResponse response);

    default String toKakaoSortType(SortType sortType) {
        return sortType.equals(SortType.ACCURACY_ORD) ? KakaoSortType.ACCURACY.getName() : KakaoSortType.RECENCY.getName();
    }
}
