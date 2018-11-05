package com.contract.front.controller;

import com.contract.common.enumClass.ContractStatus;
import com.contract.common.model.Board;
import com.contract.common.model.Contract;
import com.contract.common.param.SearchParam;
import com.contract.common.service.BoardService;
import com.contract.common.service.ContractService;
import com.contract.common.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/validate")
public class ValidationController {
    @Autowired
    private BoardService boardService;
    @Autowired
    private ContractService contractService;
    @Autowired
    private ValidationService validationService;
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
        Board board = new Board();
        Contract contract = new Contract();
        if (searchParam.getId() != null) {
            if ("file".equals(searchParam.getType())) {
                board = boardService.findById(searchParam.getId());
            }
            if ("contract".equals(searchParam.getType())) {
                Contract contract1 = contractService.findById(searchParam.getId());
                if (ContractStatus.FINISH.equals(contract1.getContractStatus())) {
                    contract = contract1;
                }
            }
        }
        model.addAttribute("board", board);
        model.addAttribute("contract", contract);
        return "validate";
    }

    @RequestMapping(value = "/check",method = RequestMethod.POST)
    public String check(MultipartFile file, Long id, String type) {

        //해당 파일의 id
        String fileName="";
        String userId="";
        String compFile = file.getOriginalFilename();
        String fileHash="";
        //multipart 파일 저장.
        validationService.saveFile(file);

        //일반 파일인지, 계약서 파일인지
        switch (type) {
            case "file":
                Board board = boardService.findById(id);

                //블록체인 검색 -> 파일이름@유저아이디
                fileName = board.getFileName();
                userId = board.getUser().getUserId();
                break;
            case "contract":
                Contract contract = contractService.findById(id);
                fileName = contract.getId() + ".docx";
                fileHash=contract.getHashValue();
                userId = contract.getUser().getUserId();
                break;
                default:
                    return "redirect:/customError.html";
        }
        if(validationService.validate(userId,fileName,compFile,fileHash)){
            return "redirect:/Success.html";
        }

        return "redirect:/Fail.html";
    }
}
