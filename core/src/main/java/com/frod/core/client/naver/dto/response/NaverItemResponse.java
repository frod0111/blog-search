package com.frod.core.client.naver.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NaverItemResponse {
    @ApiModelProperty(value = "블로그 포스트의 제목. 제목에서 검색어와 일치하는 부분은 <b> 태그로 감싸져 있습니다.")
    private String title;
    @ApiModelProperty(value = "블로그 포스트의 URL")
    private String link;
    @ApiModelProperty(value = "블로그 포스트의 내용을 요약한 패시지 정보. 패시지 정보에서 검색어와 일치하는 부분은 <b> 태그로 감싸져 있습니다.")
    private String description;
    @ApiModelProperty(value = "블로그 포스트가 있는 블로그의 이름")
    private String bloggername;
    @ApiModelProperty(value = "블로그 포스트가 있는 블로그의 주소")
    private String bloggerlink;
    @ApiModelProperty(value = "블로그 포스트가 작성된 날짜")
    private String postdate;
}


