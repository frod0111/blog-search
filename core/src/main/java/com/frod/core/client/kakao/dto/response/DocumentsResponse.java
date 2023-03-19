package com.frod.core.client.kakao.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DocumentsResponse {
    @ApiModelProperty(value = "블로그 글 제목")
    private String title;
    @ApiModelProperty(value = "블로그 글 요약")
    private String contents;
    @ApiModelProperty(value = "블로그 글 URL")
    private String url;
    @ApiModelProperty(value = "블로그의 이름")
    private String blogname;
    @ApiModelProperty(value = "검색 시스템에서 추출한 대표 미리보기 이미지 URL, 미리보기 크기 및 화질은 변경될 수 있음")
    private String thumbnail;
    @ApiModelProperty(value = "블로그 글 작성시간 [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss:SSSXXX")
    private ZonedDateTime datetime;
}
