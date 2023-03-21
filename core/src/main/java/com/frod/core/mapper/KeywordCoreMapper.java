package com.frod.core.mapper;

import com.frod.core.dto.cmd.KeywordCmd;
import com.frod.core.dto.query.KeywordQuery;
import com.frod.core.entity.KeyWordEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE

)
public interface KeywordCoreMapper {
    KeywordQuery toQuery(KeyWordEntity entity);

    KeyWordEntity toEntity(KeywordCmd cmd);
}
