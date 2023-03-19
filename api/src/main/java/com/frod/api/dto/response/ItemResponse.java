package com.frod.api.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemResponse {
    @ApiModelProperty(value = "블로그 글 제목")
    private String title;
    @ApiModelProperty(value = "블로그 글 요약")
    private String contents;
    @ApiModelProperty(value = "블로그 글 URL")
    private String url;
    @ApiModelProperty(value = "블로그의 이름")
    private String blogName;
    @ApiModelProperty(value = "대표 미리보기 이미지 URL")
    private String thumbnail;
    @ApiModelProperty(value = "블로그 글 작성시간")
    private String postedDate;
}
