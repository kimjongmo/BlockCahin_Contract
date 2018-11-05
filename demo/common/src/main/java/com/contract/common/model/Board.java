package com.contract.common.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Board extends BaseEntity {
    private String title;
    private String fileName;
    private Long fileSize;

    @ManyToOne
    private User user;
}
