package com.frod.api.dto.response;

import com.frod.api.constant.ApiType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class BlogSearchResponse {

    @ApiModelProperty(value = "총 검색 건수", example = "1")
    private Integer totalCount;

    @ApiModelProperty(value = "노출될 검색 건수", example = "1")
    private Integer pageableCount;

    @ApiModelProperty(value = "마지막 페이지 여부",example = "true")
    private Boolean isEnd;

    @ApiModelProperty(value = "응답 API 유형", example = "KAKAO")
    private ApiType apiType;

    @ApiModelProperty(value = "검색 결과 리스트")
    private List<ItemResponse> items;
}
