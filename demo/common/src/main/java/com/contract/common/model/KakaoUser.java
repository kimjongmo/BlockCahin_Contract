package com.contract.common.model;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class KakaoUser extends BaseEntity{
    private String kakaoId;
    private String kakaoEmail;
}
