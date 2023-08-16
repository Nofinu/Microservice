package com.example.userservice.service;

import com.example.userservice.entity.User;
import com.example.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User create (User user){
        return userRepository.save(user);
    }

    public User findById (int id){
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElse(null);
    }
}
