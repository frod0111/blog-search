package com.frod.api.dto.response;

import com.frod.api.constant.ApiType;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BlogSearchResponse {

    @ApiModelProperty(value = "총 검색 건수", example = "1")
    private Integer totalCount;
    @ApiModelProperty(value = "마지막 페이지 여부",example = "true")
    private Boolean isEnd;
    @ApiModelProperty(value = "응답 API 유형", example = "KAKAO")
    private ApiType apiType;
    @ApiModelProperty(value = "검색 결과 리스트")
    private List<ItemResponse> items;
}
