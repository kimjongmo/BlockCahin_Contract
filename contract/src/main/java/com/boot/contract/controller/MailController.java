package com.boot.contract.controller;

import com.boot.contract.service.EmailServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    EmailServiceImpl emailServiceImpl;

    @RequestMapping(value = "/se    nd",method = RequestMethod.GET)
    public String send(@RequestParam String to){
       //  uuid 생성
        String uuid = UUID.randomUUID().toString();

        // 테이블에 삽입한다. -> 삽입 후 리턴값은 생성된 id값.

        // url 을 작성한다.
        
        // 14라인 to 에게 메일을 보내되 body 부분에는 url 을 준다.
        emailServiceImpl.send("yoobinlee25@gmail.com", to, "Contract Master 인증 메일입니다.","");
        return to+"로 인증 메일을 발송하였습니다.";
    }

    //   value="/auth" 구현
    @RequestMapping(value="/auth",method = RequestMethod.GET)
    public String auth(@RequestParam Long id){
        log.info("id={}",id);
        return "OK";
    }


}
