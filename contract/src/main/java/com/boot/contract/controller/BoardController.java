package com.boot.contract.controller;

import com.boot.contract.model.Board;
import com.boot.contract.service.BoardService;
import com.boot.contract.util.LoginSession;
import com.boot.contract.util.Message;
import lombok.extern.slf4j.Slf4j;
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


@Slf4j
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

        List<Board> list = boardService.getList();
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
            log.info("No Session ");
            return "redirect:/board/write";
        }
        if(file.isEmpty()){
            log.info("File not found : {}",file.getName());
            model.addAttribute("messages", Message.FILE_NOT_FOUND);
            return "redirect:/board/write";
        }
        log.info("FileName : {}, title : {}",file.getName(),title);
        String userId = LoginSession.getName(session);
        Board board = new Board();
        board.setFileSize(file.getSize());
        board.setFileName(file.getName());
        board.setTitle(title);
        try{
            File rootFolder = new File(UPLOAD_FOLDER);
            if(!rootFolder.exists()){
                rootFolder.mkdirs();
            }
            File userFolder = new File(UPLOAD_FOLDER+"/"+userId);
            if(!userFolder.exists()){
                userFolder.mkdirs();
            }
            byte[] bytes = file.getBytes();
            Path path = Paths.get(userFolder.getPath()+"/"+file.getOriginalFilename());
            log.info("path : {}",path);
            Files.write(path,bytes);

        }catch(IOException e){
            log.info("Exception  Class: {}, Messages:{}",e.getClass(),e.getMessage());
        }
        boardService.save(board,userId);

        return "redirect:/board/list";
    }
}
