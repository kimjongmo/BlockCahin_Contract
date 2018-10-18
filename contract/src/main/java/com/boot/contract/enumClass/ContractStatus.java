package com.boot.contract.enumClass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ContractStatus implements BaseEnum{

        REGISTERED(0,"계약상태"),
        AUTHORIZED(3, "인증완료"),
        UNAUTHORIZED(2, "인증대기"),
        UNREGISTERED(1, "계약대기"),
        WAIT(4,"승인 대기"),
        FINISH(5,"승인 완료"),
    ;

        private Integer id;
        private String status;
}
