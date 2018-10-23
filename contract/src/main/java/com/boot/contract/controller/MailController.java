package com.boot.contract.controller;

import com.boot.contract.enumClass.ContractStatus;
import com.boot.contract.model.Contract;
import com.boot.contract.service.ContractService;
import com.boot.contract.service.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
@RequestMapping("/email")
public class MailController {

    @Autowired
    EmailServiceImpl emailServiceImpl;

    @Autowired
    ContractService contractService;

    //고용자 송신용
    @RequestMapping(value = "/send",method = RequestMethod.GET)
    public String send(@RequestParam String to, @Value("${mail.user}") String from, @RequestParam Long id){

        Contract contract = contractService.findById(id);

        String s = "http://210.123.254.206:8181/email/auth?id="+id+"&uuid="+contract.getUuid();
        String body = "<a href=\""+s+"\">"+s+"</a>";
        emailServiceImpl.send(from, to, "Contract Master 인증 메일입니다.",body);
        contract.setContractStatus(ContractStatus.UNAUTHORIZED);
        contract.setEmployerEmail(to);
        contractService.save(contract); //db에 저장
        return to+"로 인증 메일을 발송하였습니다. <a href=http://210.123.254.206:8181/contract/list>확인</a>";
    }

    //근로자 송신용
    @RequestMapping(value = "/send2",method = RequestMethod.GET)
    public String send2(@RequestParam Long id,@Value("${mail.user}") String from,@RequestParam String to){

        Contract contract = contractService.findById(id);

        String url ="http://210.123.254.206:8181/contract/contractRead?id="+id+"&uuid="+contract.getUuid();
        String body = "<p>"+contract.getEmployerName()+"님께서 계약서를 전송하였습니다. 확인부탁드립니다.</p></br>" +
                "<a href=\""+url+"\">확인하러 가기</a>";

        emailServiceImpl.send(from,to,"Contract Master 인증 메일입니다.",body);
        contract.setContractStatus(ContractStatus.WAIT);
        contract.setEmployeeEmail(to);
        contractService.save(contract);

        return to+"에게 승인 메일을 발송하였습니다. <a href=http://210.123.254.206:8181/contract/list>확인</a>";
    }

    @RequestMapping(value = "/sendFile/{id}",method = RequestMethod.GET)
    public String sendFile(@PathVariable Long id ,@Value("${mail.user}") String from){
        Contract contract = contractService.findById(id);
        String path = "c:/_contract/"+id+".docx";
        String employerEmail = contract.getEmployerEmail();
        String employeeEmail = contract.getEmployeeEmail();
        LocalDateTime time1 = contract.getUpdatedAt();
//        String time = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(time1);

        String body ="계약이 성립되었습니다. 계약 번호는 "+id+" 입니다.";
        emailServiceImpl.sendFile(from,employerEmail,"[Contract Master]계약이 성립되었습니다",body,path);
        emailServiceImpl.sendFile(from,employeeEmail,"[Contract Master]계약이 성립되었습니다",body,path);

        return "계약서 작성이 완료되었습니다. 이메일을 확인해주세요.";
    }

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public String auth(@RequestParam Long id, @RequestParam String uuid){
        Contract contract = contractService.findById(id);
        if(contract.getUuid().equals(uuid)){
            contract.setContractStatus(ContractStatus.AUTHORIZED);
            contractService.save(contract);
            String s = "[확인]";
            String confirm = "이메일이 인증되었습니다. <a href=http://210.123.254.206:8181/contract/list>"+s+"</a>";
            return confirm;
        }
        return "잘못된 접근입니다.";
    }


}
