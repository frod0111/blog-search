package com.frod.api.configuration;

import com.frod.core.common.constant.LocalCache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.stream.Collectors;

@EnableCaching
@Configuration
@RequiredArgsConstructor
public class CacheConfiguration {
    @Bean
    public CacheManager cacheManager() {
        var caches = Arrays.stream(LocalCache.values())
                .map(cache -> new CaffeineCache(cache.getCacheName(),
                        Caffeine.newBuilder()
                                .expireAfterWrite(cache.getTtl(),cache.getTtlTimeUnit())
                                .build()
                ))
                .collect(Collectors.toUnmodifiableList());

        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(caches);
        return cacheManager;
    }
}
