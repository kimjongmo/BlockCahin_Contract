package com.boot.contract.service;

import com.boot.contract.model.Board;
import com.boot.contract.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private UserService userService;

    public List<Board> getList(Long id){
        return boardRepository.findAllByUserId(id);
    }

    public void save(Board board,Long id){
        board.setUser(userService.findById(id)); // 이부분
        boardRepository.save(board);
    }

    public Board findById(Long id){
        return boardRepository.findById(id).orElse(new Board());
    }

}
