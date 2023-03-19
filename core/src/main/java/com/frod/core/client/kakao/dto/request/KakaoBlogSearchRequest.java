package com.frod.core.client.kakao.dto.request;

import com.frod.core.client.kakao.constant.KakaoSortType;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KakaoBlogSearchRequest {

    private String query;
    private String sort;
    private Integer page;
    private Integer size;
}
