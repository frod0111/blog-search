package com.frod.api.dto.request;

import com.frod.api.constant.ApiType;
import com.frod.api.constant.SortType;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BlogSearchRequest {
    @NotNull
    @NotBlank
    @ApiModelProperty(value = "검색 키워드", required = true)
    private String keyword;

    @ApiModelProperty(value = "요청 API 유형", example = "KAKAO", required = true)
    private ApiType apiType;

    @ApiModelProperty(value = "정렬 타입", example = "ACCURACY_ORD")
    private SortType sortType;

    @Range(min = 1, max = 50)
    @ApiModelProperty(value = "페이지 번호", example = "1")
    private Integer pageNo;
    @Range(min = 10, max = 50)
    @ApiModelProperty(value = "페이지당 보여줄 갯수", example = "10")
    private Integer pageSize;
}
