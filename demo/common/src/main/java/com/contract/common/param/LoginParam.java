package com.contract.common.param;

import lombok.Data;

@Data
public class LoginParam {
    private Long id; //추가
    private String userId;
    private String userPassword;
}
