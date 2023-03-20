package com.frod.core.repository;

import com.frod.core.entity.KeyWordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface KeywordRepository extends JpaRepository<KeyWordEntity,String> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT k FROM KeyWordEntity k WHERE k.keyword = :keyword")
    Optional<KeyWordEntity> findByIdForUpdate(@Param("keyword") String keyword);
    @Modifying
    @Query("UPDATE KeyWordEntity k SET k.count = k.count + 1, k.updateDate = :now WHERE k.keyword = :keyword")
    int updateCount(@Param("keyword")String keyword, @Param("now") LocalDateTime now);

    @Query("SELECT k FROM KeyWordEntity k WHERE k.updateDate > :dateBy3day ORDER BY k.count DESC,k.updateDate DESC")
    List<KeyWordEntity> findTop10AndUpdateDate(@Param("dateBy3day") LocalDateTime dateBy3day);
}
