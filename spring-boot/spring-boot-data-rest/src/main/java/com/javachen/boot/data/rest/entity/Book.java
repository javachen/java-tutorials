package com.javachen.boot.data.rest.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, length = 20, nullable = false)
    public Long id;
    @NotNull
    @Column(columnDefinition = "varchar(50) comment '书名'")
    public String name;
    @NotNull
    @Column(columnDefinition = "varchar(25) comment '作者'")
    public String author;
    @Column(columnDefinition = "varchar(255) comment '描述'")
    public String description;
    @NotNull
    @ColumnDefault("1")
    @Column(columnDefinition = "tinyint(1) comment '是否存在'")
    public Boolean status;
}