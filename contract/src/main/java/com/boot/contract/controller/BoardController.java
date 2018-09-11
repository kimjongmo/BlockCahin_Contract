package com.boot.contract.controller;

import com.boot.contract.model.Board;
import com.boot.contract.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;



    @RequestMapping("/list")
    public String list(Model model){

        List<Board> list = boardService.getList();
        model.addAttribute("board",list);

        return "board";
    }
}
