package com.boot.contract.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
public class Contract extends BaseEntity{

    @NotNull
    private String employerName;	//(갑) 성명
    private String businessType;	//사업 종류
    private String companyName;		//사업체명
    private String companyAddress;	//소재지
    private String employeeName;	//(을) 성명
    private String employeeBirth; 	// pattern : yyMMdd
    private String employeeAddress;	//주소
    private String personalNumber;	//주민등록번호
    private String payKind;	//일급, 월급, 연봉
    private String pay;		//페이
    private String day;		//주 몇일 동안 근무하는지
    private String hour;	//근무 시간
    private String location;//근무 장소
    private LocalDateTime contractalDay;	//계약 시작일
    private LocalDateTime expiredDay;	//계약 만기일
    private String registNumber;  //계약서 번호



    public Contract(String employerName, String businessType, String companyName, String companyAddress,
                    String employeeName, String employeeBirth, String employeeAddress, String personalNumber,
                    String registNumber, String payKind, String pay, String day, String hour,
                    String location, LocalDateTime contractalDay, LocalDateTime expiredDay) {
        this.employerName = employerName;
        this.businessType = businessType;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.employeeName = employeeName;
        this.employeeBirth = employeeBirth;
        this.employeeAddress = employeeAddress;
        this.personalNumber = personalNumber;
        this.registNumber = registNumber;
        this.payKind = payKind;
        this.pay = pay;
        this.day = day;
        this.hour = hour;
        this.location = location;
        this.contractalDay = contractalDay;
        this.expiredDay = expiredDay;
    }
}