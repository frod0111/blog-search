package com.frod.core.client.naver.dto.response;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NaverBlogSearchResponse {
    @ApiModelProperty(value = "검색 결과를 생성한 시간")
    private String lastBuildDate;
    @ApiModelProperty(value = "총 검색 결과 개수")
    private Integer total;
    @ApiModelProperty(value = "검색 시작 위치")
    private Integer start;
    @ApiModelProperty(value = "한 번에 표시할 검색 결과 개수")
    private Integer display;
    @ApiModelProperty(value = "개별 검색 결과")
    private List<NaverItemResponse> items;
}
