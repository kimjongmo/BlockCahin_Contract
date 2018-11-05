package com.contract.common.model;

import com.contract.common.enumClass.ContractStatus;
import com.contract.common.param.ContractParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contract extends BaseEntity{

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ContractStatus contractStatus;

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
    private LocalDateTime contractDay;	//계약 시작일
    private LocalDateTime expiredDay;	//계약 만기일
    private String employerEmail;
    private String employeeEmail;
    private String hashValue;
    @ManyToOne
    private User user;
    private String uuid;

    public void update(ContractParam contract){
        this.employerName = contract.getEmployerName();
        this.businessType = contract.getBusinessType();
        this.companyName = contract.getCompanyName();
        this.companyAddress = contract.getCompanyAddress();
        this.employeeName = contract.getEmployeeName();
        this.employeeBirth = contract.getEmployeeBirth();
        this.employeeAddress = contract.getEmployeeAddress();
        this.personalNumber = contract.getPersonalNumber();
        this.payKind = contract.getPayKind();
        this.pay = contract.getPay();
        this.day = contract.getDay();
        this.hour = contract.getHour();
        this.location = contract.getLocation();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.contractDay = LocalDateTime.parse(contract.getContractDay());
        this.expiredDay = LocalDateTime.parse(contract.getExpiredDay());
    }

}