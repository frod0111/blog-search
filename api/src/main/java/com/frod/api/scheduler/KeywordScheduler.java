package com.frod.api.scheduler;

import com.frod.core.service.KeywordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KeywordScheduler {

    private final KeywordService keywordService;
    @Scheduled(cron = "1 0 * * * *") //매일 12시 1분마다
    public void keywordDelete() {
        log.info("KeywordDelete Scheduler run!!");
        keywordService.deleteKeywordBy3day();
        log.info("KeywordDelete Scheduler end!!");
    }
}
