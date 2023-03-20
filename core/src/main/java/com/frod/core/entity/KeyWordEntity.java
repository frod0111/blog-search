package com.frod.core.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KeyWordEntity {
    @Id
    @Column(name = "KEYWORD")
    private String keyword;
    @Column(name = "COUNT")
    @ColumnDefault("0") //default 0
    private Integer count;
    @Column(name = "INSERT_DATE")
    private LocalDateTime insertDate;
    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateDate;
}
