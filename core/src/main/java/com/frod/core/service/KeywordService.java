package com.frod.core.service;

import com.frod.core.entity.KeyWordEntity;
import com.frod.core.repository.KeywordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface KeywordService {
    Boolean exitsValid(String keyword);
    void save (String keyword);
    void updateCount(String keyword);
    List<KeyWordEntity> top10SearchedList();
    void deleteKeywordBy3day();
}
