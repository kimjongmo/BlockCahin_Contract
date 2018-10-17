package com.boot.contract.controller;

import com.boot.contract.enumClass.ContractStatus;
import com.boot.contract.model.Contract;
import com.boot.contract.service.ContractService;
import com.boot.contract.service.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.UUID;

@RestController
@RequestMapping("/email")
public class MailController {

    @Autowired
    EmailServiceImpl emailServiceImpl;

    @Autowired
    ContractService contractService;

    @RequestMapping(value = "/send",method = RequestMethod.GET)
    public String send(@RequestParam String to, @Value("${mail.user}") String from, @RequestParam Long id){

        Contract contract = contractService.findById(id);

        String s = "http://localhost:8181/email/auth?id="+id+"&uuid="+contract.getUuid();
        String body = "<a href=\""+s+"\">"+s+"</a>";
        emailServiceImpl.send(from, to, "Contract Master 인증 메일입니다.",body);
        contract.setContractStatus(ContractStatus.UNAUTHORIZED);
        contractService.save(contract); //db에 저장
        return to+"로 인증 메일을 발송하였습니다.";
    }

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public String auth(@RequestParam Long id, @RequestParam String uuid){
        Contract contract = contractService.findById(id);
        if(contract.getUuid().equals(uuid)){
            contract.setContractStatus(ContractStatus.AUTHORIZED);
            contractService.save(contract);
            String s = "[확인]";
            String confirm = "이메일이 인증되었습니다. <a href=http://localhost:8181/contract/list>"+s+"</a>";
            return confirm;
        }
        return "잘못된 접근입니다.";
    }
    //   value="/auth" 구현


}
