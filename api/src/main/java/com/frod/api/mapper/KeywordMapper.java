package com.frod.api.mapper;

import com.frod.api.dto.response.RankKeywordResponse;
import com.frod.core.dto.query.KeywordQuery;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE

)
public interface KeywordMapper {
    List<RankKeywordResponse> toResponses(List<KeywordQuery> query);
}
