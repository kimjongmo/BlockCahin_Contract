package com.boot.contract.service;


import com.boot.contract.model.User;
import com.boot.contract.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findById(Long id){ //여기
        return userRepository.findById(id).orElse(new User()); //여기
    }

    public boolean checkUserId(String userId){ //여기
        return userRepository.existsById(userId); //여기
    }

    public void save(User user){
        userRepository.save(user);
    }
}
