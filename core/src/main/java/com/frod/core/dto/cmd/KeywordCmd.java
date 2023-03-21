package com.frod.core.dto.cmd;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class KeywordCmd {
    private String keyword;
    private Integer count;
    private LocalDateTime insertDate;
    private LocalDateTime updateDate;
}
