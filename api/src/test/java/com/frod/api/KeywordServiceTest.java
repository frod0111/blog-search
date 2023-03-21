package com.frod.api;

import com.frod.core.dto.cmd.KeywordCmd;
import com.frod.core.dto.query.KeywordQuery;
import com.frod.core.repository.KeywordRepository;
import com.frod.core.service.impl.KeywordServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class KeywordServiceTest {

    @Autowired
    private KeywordServiceImpl keywordService;

    @Autowired
    KeywordRepository repository;
    LocalDateTime now = LocalDateTime.now();

    @AfterEach
    public void init() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("검색어_저장_테스트")
    public void keywordSaveTest() {
        //given
        String keyword = "테스트";
        var cmd = KeywordCmd.builder()
                .keyword(keyword)
                .count(1)
                .insertDate(now)
                .updateDate(now)
                .build();

        //when & then
        keywordService.save(cmd);
        var result = keywordService.exitsValid(keyword);
        Assertions.assertThat(result).isTrue();
    }

    @Test
    @DisplayName("검색어_카운트_업데이트_테스트")
    public void keywordCountUpdateTest() {
        //given
        String keyword = "테스트";
        var cmd = KeywordCmd.builder()
                .keyword(keyword)
                .count(1)
                .insertDate(now)
                .updateDate(now)
                .build();
        var uptCmd = KeywordCmd.builder().keyword(keyword).build();

        //when & then
        keywordService.save(cmd);
        keywordService.updateCount(uptCmd);

        var result = keywordService.findById(keyword);

        Assertions.assertThat(result.getCount()).isEqualTo(2);
    }

    @Test
    @DisplayName("인기검색어_TOP10_조회_테스트")
    public void rankTest() {
        //given
        var cmdList = List.of(KeywordCmd.builder()
                        .keyword("테스트")
                        .count(1)
                        .insertDate(now)
                        .updateDate(now)
                        .build(),
                KeywordCmd.builder()
                        .keyword("테스트2")
                        .count(3)
                        .insertDate(now)
                        .updateDate(now)
                        .build(),
                KeywordCmd.builder()
                        .keyword("테스트3")
                        .count(2)
                        .insertDate(now)
                        .updateDate(now)
                        .build(),
                KeywordCmd.builder()
                        .keyword("테스트4")
                        .count(5)
                        .insertDate(now.minusDays(5)) //5일전 데이터로 세팅
                        .updateDate(now.minusDays(5))
                        .build());


        //when
        for (var cmd : cmdList) {
            keywordService.save(cmd);
        }

        var result = keywordService.top10SearchedList();

        //then
        Assertions.assertThat(result)
                .extracting(KeywordQuery::getKeyword)
                .containsOnly("테스트", "테스트2", "테스트3");
    }
}
