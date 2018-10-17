package com.boot.contract.controller;

import com.boot.contract.enumClass.ContractStatus;
import com.boot.contract.model.Contract;
import com.boot.contract.service.ContractService;
import com.boot.contract.service.EmailServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@Slf4j
@RestController
@RequestMapping("/email")
public class MailController {

    @Autowired
    private EmailServiceImpl emailServiceImpl;
    @Autowired
    private ContractService contractService;
    @RequestMapping(value = "/send",method = RequestMethod.GET)
    public String send(@RequestParam String to, @RequestParam Long id, @Value("${mail.user}") String from){

        //테이블에서 값을 받아와서
        Contract contract = contractService.findById(id);

        // url 을 작성한다.
        String url ="http://localhost:8181/email/auth?id="+contract.getId()+"&uuid="+contract.getUuid();
        String htmlContent = "<a href=\""+url+"\">"+url+"</a>";
        

        //from 은 소스 상에 있는 것보다는 프로퍼티 상에서 관리하는게 좋다.
        emailServiceImpl.send(from, to, "Contract Master 인증 메일입니다.",htmlContent);
        contract.setContractStatus(ContractStatus.UNAUTHORIZED);
        contractService.save(contract);
        return to+"로 인증 메일을 발송하였습니다.";
    }







    //   value="/auth" 구현
    @RequestMapping(value="/auth",method = RequestMethod.GET)
    public String auth(@RequestParam Long id,@RequestParam String uuid){
        log.info("id={}",id);
        Contract contract = contractService.findById(id);
        if(contract.getUuid().equals(uuid)){
            contract.setContractStatus(ContractStatus.AUTHORIZED);
            contractService.save(contract);
            return "인증완료 되었습니다.";
        }

        return "잘못된 접근입니다.";
    }


}
