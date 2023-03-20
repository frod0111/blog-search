package com.frod.core.service.impl;

import com.frod.core.common.constant.LocalCache;
import com.frod.core.entity.KeyWordEntity;
import com.frod.core.repository.KeywordRepository;
import com.frod.core.service.KeywordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeywordServiceImpl implements KeywordService {
    private final KeywordRepository keywordRepository;
    @Override
    @Transactional(readOnly = true)
    public Boolean exitsValid(String keyword) {
        return keywordRepository.findByIdForUpdate(keyword).isPresent();
    }

    @Override
    @Transactional
    public void save (String keyword) {
        var now = LocalDateTime.now();
        keywordRepository.save(KeyWordEntity.builder().keyword(keyword).count(1).insertDate(now).updateDate(now).build());
    }

    @Override
    @Transactional
    public void updateCount(String keyword) {
        keywordRepository.updateCount(keyword, LocalDateTime.now());
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "rank_top_10",cacheManager = "cacheManager")
    public List<KeyWordEntity> top10SearchedList() {
        var dateBy3day = LocalDateTime.now().minusDays(3);
        return keywordRepository.findTop10AndUpdateDate(dateBy3day);
    }
    @Override
    @Transactional
    public void deleteKeywordBy3day() {
        var dateBy3day = LocalDateTime.now().minusDays(3);
        keywordRepository.deleteKeywordBy3day(dateBy3day);
    }

}
