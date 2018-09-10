package com.boot.contract.model;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Board extends BaseEntity {
    private String title;
    private String fileName;
    private int fileSize;
    private String userId;
//    @ManyToOne
//    private User user;
}
