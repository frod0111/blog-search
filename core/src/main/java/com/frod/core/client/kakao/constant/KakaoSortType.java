package com.frod.core.client.kakao.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum KakaoSortType {
    ACCURACY("accuracy"),
    RECENCY("recency");

    private final String name;
}
