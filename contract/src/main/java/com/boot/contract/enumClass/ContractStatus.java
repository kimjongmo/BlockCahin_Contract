package com.boot.contract.enumClass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ContractStatus implements BaseEnum{
    REGISTERED(0,"계약등록"),
    AUTHORIZED(3,"인증완료"),
    UNAUTHORIZED(2,"인증대기"),
    UNREGISTERED(1,"계약대기"),
    ;

        private Integer id;
        private String status;
}
