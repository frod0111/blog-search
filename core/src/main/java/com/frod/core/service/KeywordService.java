package com.frod.core.service;

import com.frod.core.entity.KeyWordEntity;
import com.frod.core.repository.KeywordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeywordService {
    private final KeywordRepository keywordRepository;
    @Transactional(readOnly = true)
    public Boolean exitsValid(String keyword) {
        return keywordRepository.findByIdForUpdate(keyword).isPresent();
    }

    @Transactional
    public void save (String keyword) {
        keywordRepository.save(KeyWordEntity.builder().keyword(keyword).count(1).build());
    }

    @Transactional
    public void updateCount(String keyword) {
        keywordRepository.updateCount(keyword);
    }

    @Transactional(readOnly = true)
    public List<KeyWordEntity> top10SearchedList() {
        return keywordRepository.findTop10ByOrderByCountDesc();
    }
}
