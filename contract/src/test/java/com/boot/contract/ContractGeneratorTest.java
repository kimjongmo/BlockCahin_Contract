package com.boot.contract;

import com.boot.contract.model.Contract;
import com.boot.contract.util.ContractGenerator;
import com.boot.contract.util.FileHashExtractor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@RunWith(SpringRunner.class)
public class ContractGeneratorTest {

    @Test
    public void test1() throws Exception {
        String filePath = "c:/_contract/테스트3.docx";
        String value = FileHashExtractor.extractFileHashSHA256(filePath);

        log.info("validate={}","026f4333cf 27ca1fbbfa b35f4ca1ce 8282bbc9d4 1e08126641 f75bddcb07 ab28".equals(value));
    }

    @Test
    public void test() {
        String fileName = "테스트3";
        Contract contract = new Contract();
        contract.setEmployerName("비트코인");
        contract.setBusinessType("잡화");
        contract.setCompanyName("롯데");
        contract.setCompanyAddress("경기도 남양주시");
        contract.setEmployeeName("김미래");
        contract.setEmployeeBirth("930101");
        contract.setEmployeeAddress("서울시 성수동");
        contract.setPersonalNumber("930101-1010100");
        contract.setPayKind("월급");
//        contract.setPay("700000");
//        contract.setDay("5");
//        contract.setHour("7");
        contract.setLocation("서울시 강남구");
        contract.setContractDay(LocalDateTime.now().minusDays(100));
        contract.setExpiredDay(LocalDateTime.now().minusDays(10));
        try {
            //계약서(docx) 생성
            ContractGenerator.create(contract,fileName);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
