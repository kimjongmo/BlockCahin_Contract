package com.boot.contract.controller;

import com.boot.contract.enumClass.ContractStatus;
import com.boot.contract.model.Board;
import com.boot.contract.model.Contract;
import com.boot.contract.param.SearchParam;
import com.boot.contract.service.BoardService;
import com.boot.contract.service.ContractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Slf4j
@Controller
@RequestMapping("/validate")
public class ValidationController {
    @Autowired
    private BoardService boardService;
    @Autowired
    private ContractService contractService;

    //검증 페이지 요청
    @RequestMapping(value = "/pages", method = RequestMethod.GET)
    public String pages(Model model) {
        model.addAttribute("board", new Board());
        model.addAttribute("contract", new Contract());
        return "validate";
    }

    //검색
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(SearchParam searchParam, Model model) {
        log.info("searchParam : {}", searchParam.toString());
        Board board = new Board();
        Contract contract = new Contract();
        if (searchParam.getId() != null) {
            if ("file".equals(searchParam.getType())) {
                board = boardService.findById(searchParam.getId());
            }
            if ("contract".equals(searchParam.getType())) {
                Contract contract1 = contractService.findById(searchParam.getId());
                if(ContractStatus.FINISH.equals(contract1.getContractStatus())){
                    contract = contract1;
                }
            }
        }
        model.addAttribute("board", board);
        model.addAttribute("contract", contract);
        return "validate";
    }

}
