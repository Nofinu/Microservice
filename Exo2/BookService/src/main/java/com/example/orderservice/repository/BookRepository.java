package com.example.orderservice.repository;

import com.example.orderservice.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book,Integer> {
    List<Book> findByAuthorId (int id);
}
