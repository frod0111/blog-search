package com.frod.api.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SortType {
    ACCURACY_ORD("정확도순"),
    LATEST_ORD("최신순");
    private final String name;
}
