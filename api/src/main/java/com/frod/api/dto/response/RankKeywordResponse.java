package com.frod.api.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RankKeywordResponse {
    @ApiModelProperty(value = "검색어", example = "검색어")
    private String keyword;
    @ApiModelProperty(value = "검색 횟수", example = "1")
    private Integer count;
    @ApiModelProperty(value = "검색어 등록 시간", example = "2023-03-21T16:00.32.714Z")
    private LocalDateTime insertDate;
    @ApiModelProperty(value = "검색어 수정 시간", example = "2023-03-21T16:00.32.714Z")
    private LocalDateTime updateDate;
}
