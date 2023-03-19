package com.frod.core.client.kakao.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MetaResponse {
    @ApiModelProperty(value = "검색된 문서 수")
    private Integer total_count;
    @ApiModelProperty(value = "total_count 중 노출 가능 문서 수")
    private Integer pageable_count;
    @ApiModelProperty(value = "현재 페이지가 마지막 페이지인지 여부, 값이 false면 page를 증가시켜 다음 페이지를 요청할 수 있음")
    private Boolean is_end;
}
