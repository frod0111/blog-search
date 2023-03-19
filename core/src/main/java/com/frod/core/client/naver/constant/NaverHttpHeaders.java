package com.frod.core.client.naver.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NaverHttpHeaders {
    NAVER_CLIENT_ID("X-Naver-Client-Id"),
    NAVER_CLIENT_SECRET("X-Naver-Client-Secret");
    private final String headerName;
}
