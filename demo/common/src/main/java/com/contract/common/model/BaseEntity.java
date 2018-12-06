package com.contract.common.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass// 엔티티 생성 하지 않음.
@Data
@EntityListeners({JpaListener.class})// 생성, 업데이트 리스너
public class BaseEntity implements Serializable {

    //데이터 직렬화
    private static final long serialVersionUID = 9102920271317370386L;

    //테이블 공통
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
