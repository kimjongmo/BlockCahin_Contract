package com.contract.front.controller;

import com.contract.common.model.Board;
import com.contract.common.service.BoardService;
import com.contract.common.util.LoginSession;
import com.contract.common.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@Controller
@RequestMapping("/board")
public class BoardController {

    private static final String UPLOAD_FOLDER = "C:/contract"; //이부분 폴더 명 수정

    @Autowired
    private BoardService boardService;

    @RequestMapping("/list")
    public String list(Model model,HttpSession session){
        if(!LoginSession.isLogin(session))
            return "redirect:/login/form";

        List<Board> list = boardService.getList(LoginSession.getId(session));
        model.addAttribute("board",list);

        return "board";
    }

    @RequestMapping("/write")
    public String write(HttpSession session){
        if(LoginSession.isLogin(session))
                return "write";
        else
            return "redirect:/login/form";
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public String create(@RequestParam MultipartFile file,@RequestParam String title, HttpSession session, Model model){

        if(!LoginSession.isLogin(session)){
            return "redirect:/board/write";
        }
        if(file.isEmpty()){
            model.addAttribute("messages", Message.FILE_NOT_FOUND);
            return "redirect:/board/write";
        }
        Long id = LoginSession.getId(session); //이부분 추가
        String userId = LoginSession.getName(session);
        Board board = new Board();
        board.setFileSize(file.getSize());
        board.setFileName(file.getOriginalFilename());
        board.setTitle(title);
        try{
            File rootFolder = new File(UPLOAD_FOLDER+"/"+userId);
            if(!rootFolder.exists()){
                rootFolder.mkdirs();
            }
            byte[] bytes = file.getBytes();
            Path path = Paths.get(rootFolder.getPath()+"/"+file.getOriginalFilename());
            Files.write(path,bytes);

        }catch(IOException e){
        }
        boardService.save(board, id); //이 부분

        return "redirect:/board/list";
    }
}
