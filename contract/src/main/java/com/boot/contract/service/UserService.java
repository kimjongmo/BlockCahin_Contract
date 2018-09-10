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

    public User findByUserId(String userId){
        return userRepository.findByUserId(userId).orElse(new User());
    }

    public boolean checkUserId(String userId){
        return userRepository.existsByUserId(userId);
    }

    public void save(User user){
        userRepository.save(user);
    }
}
