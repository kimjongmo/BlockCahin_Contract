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

    public List<Board> getList(){
        return boardRepository.findAll();
    }
}
