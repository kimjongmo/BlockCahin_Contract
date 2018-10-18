package com.boot.contract.param;

import lombok.Data;

@Data
public class ContractParam {
    private String employerName;	//(갑) 성명
    private String businessType;	//사업 종류
    private String companyName;		//사업체명
    private String companyAddress;	//소재지
    private String employeeName;	//(을) 성명
    private String employeeBirth; 	// pattern : yyMMdd
    private String employeeAddress;	//주소
    private String personalNumber;	//주민등록번호
    private String payKind;	//일급, 월급, 연봉
    private int pay;		//페이
    private int day;		//주 몇일 동안 근무하는지
    private int hour;	//근무 시간
    private String location;//근무 장소
    private String contractDay;	//계약 시작일
    private String expiredDay;	//계약 만기일
}
