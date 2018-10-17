package com.boot.contract.controller;

import com.boot.contract.enumClass.ContractStatus;
import com.boot.contract.model.Contract;
import com.boot.contract.model.User;
import com.boot.contract.service.ContractService;
import com.boot.contract.service.UserService;
import com.boot.contract.util.LoginSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/contract")
public class ContractController {
    @Autowired
    private ContractService contractService;
    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public String contractList(HttpSession session, Model model){
        if(!LoginSession.isLogin(session))
            return "redirect:/login/form";
        Long id = LoginSession.getId(session);
        List<Contract> contracts = contractService.selectAll(id);
        model.addAttribute("list",contracts);

        return "contract";
    }

    @RequestMapping("/view")
    public String view(@RequestParam Long id, HttpServletResponse response){
        Contract contract = contractService.findById(id);

        if(contract.getId() == null){
            return "redirect:/contract/list";
        }
        //아직 이메일을 보내지 않은 상태
        if(contract.getContractStatus().equals(ContractStatus.UNREGISTERED)){
            return "redirect:/contract/checkEmail/"+id;
        }else if(contract.getContractStatus().equals(ContractStatus.UNAUTHORIZED)){
            response.setContentType("text/html; charset=utf-8");
            try {
                PrintWriter out = response.getWriter();
                out.println("<script>alert('이메일 인증을 부탁드려요.'); history.go(-1);</script>");
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            return "albaContract";
        }
        return null;
    }


    // 작성자의 이메일 확인
    // 메일로 보낸다.
    @RequestMapping("/checkEmail/{id}")
    public String checkEmail(HttpSession session, @PathVariable Long id, Model model){
        if(!LoginSession.isLogin(session))
            return "redirect:/login/form";
        //메시지 전송한다.
        //모델에 "이메일로 전송했음"을 넣어 html 에서 알린다.
        model.addAttribute("val",id);
        return "checkEmail";
    }


    // 작성 페이지
    @RequestMapping("/write")
    public String write(HttpSession session){
        if(!LoginSession.isLogin(session))
            return "redirect:/login/form";

        Contract contract = new Contract();


        //Long id = session.getAttribute("id");
        Long id = LoginSession.getId(session);
        User user = userService.findById(id);

        contract.setUser(user);
        contract.setContractStatus(ContractStatus.UNREGISTERED);
        contract.setUuid(UUID.randomUUID().toString());

        contractService.save(contract);
        return "redirect:/contract/list";
    }

}
