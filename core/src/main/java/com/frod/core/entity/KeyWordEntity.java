package com.frod.core.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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
}
