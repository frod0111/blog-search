package com.frod.core.service;

import com.frod.core.dto.cmd.KeywordCmd;
import com.frod.core.dto.query.KeywordQuery;
import com.frod.core.entity.KeyWordEntity;

import java.util.List;

public interface KeywordService {
    Boolean exitsValid(String keyword);

    void save(KeywordCmd cmd);

    void updateCount(KeywordCmd cmd);

    List<KeywordQuery> top10SearchedList();

    void deleteKeywordBy3day();

    KeywordQuery findById(String keyword);
}
