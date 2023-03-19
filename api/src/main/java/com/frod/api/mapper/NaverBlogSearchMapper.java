package com.frod.api.mapper;

import com.frod.api.constant.SortType;
import com.frod.api.dto.request.BlogSearchRequest;
import com.frod.api.dto.response.BlogSearchResponse;
import com.frod.api.dto.response.ItemResponse;
import com.frod.core.client.naver.constant.NaverSortType;
import com.frod.core.client.naver.dto.request.NaverBlogSearchRequest;
import com.frod.core.client.naver.dto.response.NaverBlogSearchResponse;
import com.frod.core.client.naver.dto.response.NaverItemResponse;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE

)
public interface NaverBlogSearchMapper {
    @Mapping(target = "query", source = "request.keyword")
    @Mapping(target = "sort",  expression = "java(toNaverSortType(request.getSortType()))")
    @Mapping(target = "start",  source = "request.pageNo")
    @Mapping(target = "display",  source = "request.pageSize")
    NaverBlogSearchRequest toNaverBlogSearchRequest(BlogSearchRequest request);

    @Mapping(target = "title", source = "naverItemResponse.title")
    @Mapping(target = "contents", source = "naverItemResponse.description")
    @Mapping(target = "url", source = "naverItemResponse.bloggerlink")
    @Mapping(target = "blogName", source = "naverItemResponse.bloggername")
    @Mapping(target = "postedDate", source = "naverItemResponse.postdate")
    ItemResponse toItemResponse(NaverItemResponse naverItemResponse);

    @Mapping(target = "totalCount", source = "response.total")
    @Mapping(target = "isEnd", expression = "java(toIsEnd(response.getTotal(),pageNo,pageSize))")
    @Mapping(target = "apiType", constant = "NAVER")
    @Mapping(target = "items", source = "response.items")
    BlogSearchResponse toBlogSearchResponse(NaverBlogSearchResponse response, Integer pageNo, Integer pageSize);

    default String toNaverSortType(SortType sortType) {
        return sortType.equals(SortType.ACCURACY_ORD) ? NaverSortType.SIM.getName() : NaverSortType.DATE.getName();
    }

    default Boolean toIsEnd(Integer total, Integer pageNo, Integer pageSize) {
        return pageNo * pageSize >= total;
    }
}
