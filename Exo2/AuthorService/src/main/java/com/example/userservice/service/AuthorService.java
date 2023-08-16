package com.example.userservice.service;

import com.example.userservice.entity.Author;
import com.example.userservice.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author create (Author author){
        return authorRepository.save(author);
    }

    public Author findById (int id){
        Optional<Author> authorOptional = authorRepository.findById(id);
        return authorOptional.orElse(null);
    }

    public List<Author> findAll (){
        return (List<Author>) authorRepository.findAll();
    }

    public Author findByAuthorName(String name){
        return authorRepository.findFirstByFirstName(name);
    }
}
