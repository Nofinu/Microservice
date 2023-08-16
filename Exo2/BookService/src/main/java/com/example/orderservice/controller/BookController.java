package com.example.orderservice.controller;

import com.example.orderservice.dto.BookRequestDto;
import com.example.orderservice.dto.BookResponseDto;
import com.example.orderservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/create")
    public ResponseEntity<BookResponseDto> create (@RequestBody BookRequestDto bookRequestDto){
        return ResponseEntity.ok(bookService.create(bookRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> findById(@PathVariable int id){
        return ResponseEntity.ok(bookService.findById(id));
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<BookResponseDto>> findByProduct(@PathVariable String author){
        return ResponseEntity.ok(bookService.findByAuthor(author));
    }
}
