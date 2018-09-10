package com.boot.contract.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

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
