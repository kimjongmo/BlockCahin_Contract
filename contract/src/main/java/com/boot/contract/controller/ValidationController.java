package com.boot.contract.controller;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/validate")
public class ValidationController {

    //검증 페이지 요청
    @RequestMapping("/pages")
    public String pages(){
        return "validate";
    }
    //검증 요청

}
