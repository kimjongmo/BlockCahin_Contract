package com.boot.contract.model;

import com.boot.contract.enumClass.ContractStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    private String pay;		//페이
    private String day;		//주 몇일 동안 근무하는지
    private String hour;	//근무 시간
    private String location;//근무 장소
    private LocalDateTime contractDay;	//계약 시작일
    private LocalDateTime expiredDay;	//계약 만기일
    private String registNumber;  //계약서 번호
    @ManyToOne
    private User user;
    private String uuid;


}