package com.frod.core.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.TimeUnit;

@Getter
@RequiredArgsConstructor
public enum LocalCache {
    RANK_TOP_10("rank_top_10",2L, TimeUnit.SECONDS);

    private final String cacheName;
    private final long ttl;
    private final TimeUnit ttlTimeUnit;
}
