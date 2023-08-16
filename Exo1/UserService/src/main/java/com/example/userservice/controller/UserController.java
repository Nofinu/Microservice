package com.example.userservice.controller;

import com.example.userservice.entity.User;
import com.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> createUser (@RequestBody User user){
        User userCreate = userService.create(user);
        if(userCreate != null){
            return ResponseEntity.ok(userCreate);
        }
        return new ResponseEntity<>(userCreate, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping("/{id}")
    public ResponseEntity<User> findUser (@PathVariable int id){
        User userFind = userService.findById(id);
        if(userFind!=null){
            return ResponseEntity.ok(userFind);
        }
        return new ResponseEntity<>(userFind,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
