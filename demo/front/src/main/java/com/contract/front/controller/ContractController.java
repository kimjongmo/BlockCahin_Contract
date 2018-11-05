package com.contract.front.controller;

import com.contract.common.enumClass.ContractStatus;
import com.contract.common.model.Contract;
import com.contract.common.model.User;
import com.contract.common.param.ContractParam;
import com.contract.common.service.ContractService;
import com.contract.common.service.UserService;
import com.contract.common.util.ContractGenerator;
import com.contract.common.util.FileHashExtractor;
import com.contract.common.util.LoginSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public String contractList(HttpSession session, Model model) {
        if (!LoginSession.isLogin(session))
            return "redirect:/login/form";
        Long id = LoginSession.getId(session);
        List<Contract> contracts = contractService.selectAll(id);
        model.addAttribute("list", contracts);

        return "contract";
    }

    @RequestMapping("/view")
    public String view(@RequestParam Long id, HttpServletResponse response, Model model) {
        Contract contract = contractService.findById(id);

        if (contract.getId() == null) {
            return "redirect:/contract/list";
        }
        //아직 이메일을 보내지 않은 상태
        if (contract.getContractStatus().equals(ContractStatus.UNREGISTERED)) {
            return "redirect:/contract/checkEmail/" + id;
        } else if (contract.getContractStatus().equals(ContractStatus.UNAUTHORIZED)) {
            response.setContentType("text/html; charset=utf-8");
            try {
                PrintWriter out = response.getWriter();
                out.println("<script>alert('이메일 인증을 부탁드려요.'); history.go(-1);</script>");
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (contract.getContractStatus().equals(ContractStatus.AUTHORIZED)) {
            model.addAttribute("val", contract.getId());
            return "albaContractWrite";
        } else {
            model.addAttribute("contract", contract);
            return "albaContract";
        }
        return null;
    }


    // 작성자의 이메일 확인
    // 메일로 보낸다.
    @RequestMapping("/checkEmail/{id}")
    public String checkEmail(HttpSession session, @PathVariable Long id, Model model) {
        if (!LoginSession.isLogin(session))
            return "redirect:/login/form";
        //메시지 전송한다.
        //모델에 "이메일로 전송했음"을 넣어 html 에서 알린다.
        model.addAttribute("val", id);
        return "checkEmail";
    }

    //
    @RequestMapping(value = "/sendToEmail/{id}", method = RequestMethod.GET)
    public String sendToEmail(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "sendToEmail";
    }


    // 작성 페이지
    @RequestMapping("/write")
    public String write(HttpSession session) {
        if (!LoginSession.isLogin(session))
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


    //자바스크립트로 데이터 체크했다는 가정 하에 진행

    @RequestMapping(value = "/write2", method = RequestMethod.POST)
    public String write2(@RequestParam Long id, ContractParam contractParam) {


        Contract original = contractService.findById(id);

        //내용 업데이트
        original.update(contractParam);

        contractService.save(original);

        return "redirect:/contract/sendToEmail/" + id;
    }

    @RequestMapping(value = "contractRead", method = RequestMethod.GET)
    public String contractRead(@RequestParam Long id, @RequestParam String uuid, Model model) {
        Contract contract = contractService.findById(id);

        //대기 상태가 아니라면
        if (!ContractStatus.WAIT.equals(contract.getContractStatus()))
            return null;

        if (contract.getUuid().equals(uuid)) {
            model.addAttribute("contract", contract);
            return "albaContractRead";
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(@RequestParam Long id) {
        Contract contract = contractService.findById(id);

        //파일 생성
        try {
            ContractGenerator.create(contract, contract.getId().toString());
            String hashValue = FileHashExtractor.extractFileHashSHA256("c:/_contract/"+contract.getUser().getUserId()+"/"+id+".docx");
            contract.setHashValue(hashValue);
            //chainCode.insertBlock("c:/_contract/"+contract.getUser().getUserId()+"/"+id+".docx");
        } catch (Exception e) {
            e.printStackTrace();
        }
        contract.setContractStatus(ContractStatus.FINISH);
        contractService.save(contract);


        return "redirect:/email/sendFile/"+id;
    }

}
