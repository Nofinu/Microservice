package com.example.userservice.repository;

import com.example.userservice.entity.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author,Integer>{
    Author findFirstByFirstName (String name);
}
