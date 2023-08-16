package com.example.userservice.controller;

import com.example.userservice.entity.Author;
import com.example.userservice.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/author")
public class BookController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/create")
    public ResponseEntity<Author> createAuthor (@RequestBody Author author){
        Author authorCreate = authorService.create(author);
        if(authorCreate != null){
            return ResponseEntity.ok(authorCreate);
        }
        return new ResponseEntity<>(authorCreate, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping("/{id}")
    public ResponseEntity<Author> findUser (@PathVariable int id){
        Author authorFind = authorService.findById(id);
        if(authorFind !=null){
            return ResponseEntity.ok(authorFind);
        }
        return new ResponseEntity<>(authorFind,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping("")
    public ResponseEntity<List<Author>> findAll(){
        return ResponseEntity.ok(authorService.findAll());
    }

    @RequestMapping("/name/{name}")
    public ResponseEntity<Author> findByName(@PathVariable String name){
        return ResponseEntity.ok(authorService.findByAuthorName(name));
    }
}
