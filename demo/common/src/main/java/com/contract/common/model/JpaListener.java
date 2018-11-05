package com.contract.common.model;

import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Component
public class JpaListener {
    @PrePersist
    public void create(BaseEntity baseEntity){
        baseEntity.setCreatedAt(LocalDateTime.now());
    }
    @PreUpdate
    public void update(BaseEntity baseEntity){
        baseEntity.setUpdatedAt(LocalDateTime.now());
    }
}
