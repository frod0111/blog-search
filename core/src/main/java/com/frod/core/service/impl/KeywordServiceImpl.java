package com.frod.core.service.impl;

import com.frod.core.common.constant.LocalCache;
import com.frod.core.dto.cmd.KeywordCmd;
import com.frod.core.dto.query.KeywordQuery;
import com.frod.core.entity.KeyWordEntity;
import com.frod.core.mapper.KeywordMapper;
import com.frod.core.repository.KeywordRepository;
import com.frod.core.service.KeywordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeywordServiceImpl implements KeywordService {
    private final KeywordRepository keywordRepository;
    private final KeywordMapper keywordMapper;

    @Override
    @Transactional(readOnly = true)
    public Boolean exitsValid(String keyword) {
        return keywordRepository.findByIdForUpdate(keyword).isPresent();
    }

    @Override
    @Transactional
    public void save(KeywordCmd cmd) {
        keywordRepository.save(keywordMapper.toEntity(cmd));
    }

    @Override
    @Transactional
    public void updateCount(KeywordCmd cmd) {
        keywordRepository.updateCount(cmd.getKeyword(), LocalDateTime.now());
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "rank_top_10", cacheManager = "cacheManager")
    public List<KeywordQuery> top10SearchedList() {
        var dateBy3day = LocalDateTime.now().minusDays(3);
        return keywordRepository.findTop10AndUpdateDate(dateBy3day).stream()
                .map(keywordMapper::toQuery)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    @Transactional
    public void deleteKeywordBy3day() {
        var dateBy3day = LocalDateTime.now().minusDays(3);
        keywordRepository.deleteKeywordBy3day(dateBy3day);
    }

    @Override
    @Transactional(readOnly = true)
    public KeywordQuery findById(String keyword) {
        return keywordMapper.toQuery(keywordRepository.findById(keyword).orElse(new KeyWordEntity()));
    }
}
