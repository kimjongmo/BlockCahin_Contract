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

    public List<Board> getList(){
        return boardRepository.findAll();
    }

    public void save(Board board,String userId){
        board.setUser(userService.findByUserId(userId));
        boardRepository.save(board);
    }

}
