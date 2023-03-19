package com.frod.core.client.naver.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NaverSortType {
    SIM("sim"),
    DATE("date");

    private final String name;
}
