package com.frod.core.dto.query;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class KeywordQuery {
    private String keyword;
    private Integer count;
    private LocalDateTime insertDate;
    private LocalDateTime updateDate;
}
