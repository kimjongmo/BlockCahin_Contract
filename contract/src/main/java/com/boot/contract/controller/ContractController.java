package com.boot.contract.controller;

import com.boot.contract.service.ContractService;
import com.boot.contract.util.LoginSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/contract")
public class ContractController {
    @Autowired
    private ContractService contractService;


    // 작성자의 이메일 확인
    // 메일로 보낸다.
    @RequestMapping("/checkEmail")
    public String checkEmail(HttpSession session){
        if(!LoginSession.isLogin(session))
            return "redirect:/login/form";
        //메시지 전송한다.
        //모델에 "이메일로 전송했음"을 넣어 html 에서 알린다.
        return "checkEmail";
    }


    // 작성 페이지
    @RequestMapping("/write")
    public String write(HttpSession session){
        if(!LoginSession.isLogin(session))
            return "redirect:/login/form";
        return "albaContract";
    }

}
