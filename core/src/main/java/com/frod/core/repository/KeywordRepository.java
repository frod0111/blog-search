package com.frod.core.repository;

import com.frod.core.entity.KeyWordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

public interface KeywordRepository extends JpaRepository<KeyWordEntity,String> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT k FROM KeyWordEntity k WHERE k.keyword = :keyword")
    Optional<KeyWordEntity> findByIdForUpdate(@Param("keyword") String keyword);
    @Modifying
    @Query("UPDATE KeyWordEntity k SET k.count = k.count + 1 WHERE k.keyword = :keyword")
    int updateCount(@Param("keyword")String keyword);

    List<KeyWordEntity> findTop10ByOrderByCountDesc();
}
