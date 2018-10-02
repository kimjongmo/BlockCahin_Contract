package com.boot.contract.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class MailController {
    //메일 서비스

    //api
    @RequestMapping(value = "/send",method = RequestMethod.GET)
    public String send(@RequestParam String to){
        return to+"로 인증 메일을 발송하였습니다.";
    }


}
