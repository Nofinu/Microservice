package com.example.orderservice.service;

import com.example.orderservice.dto.BookRequestDto;
import com.example.orderservice.dto.BookResponseDto;
import com.example.orderservice.dto.Author;
import com.example.orderservice.entity.Book;
import com.example.orderservice.repository.BookRepository;
import com.example.orderservice.tools.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    private final RestClient<Author> restClient;

    public BookService() {
        this.restClient = new RestClient<>();
    }

    public BookResponseDto create(BookRequestDto bookRequestDto) {
        Author author = restClient.get("author/" + bookRequestDto.getAuthorId(), Author.class);
        if (author != null) {
            Book book = Book.builder().id(bookRequestDto.getId())
                    .title(bookRequestDto.getTitle())
                    .isbn(bookRequestDto.getIsbn())
                    .publishedDate(LocalDate.parse(bookRequestDto.getPublishedDate()))
                    .authorId(bookRequestDto.getAuthorId())
                    .build();

            Book bookCreate = bookRepository.save(book);

            return BookResponseDto.builder().id(bookCreate.getId())
                    .title(book.getTitle())
                    .isbn(book.getIsbn())
                    .publishedDate(book.getPublishedDate())
                    .author(author).build();
        }
        return null;
    }

    public BookResponseDto findById(int id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            Author author = restClient.get("author/" + bookOptional.get().getAuthorId(), Author.class);
            return BookResponseDto.builder()
                    .id(bookOptional.get().getId())
                    .title(bookOptional.get().getTitle())
                    .author(author)
                    .publishedDate(bookOptional.get().getPublishedDate())
                    .isbn(bookOptional.get().getIsbn())
                    .build();
        }
        return null;
    }

    public List<BookResponseDto> findByAuthor(String name) {
        Author author = restClient.get("author/name/" + name, Author.class);
        List<BookResponseDto> bookResponseDtos = new ArrayList<>();
        if (author != null) {
            bookRepository.findByAuthorId(author.getId()).stream().forEach(b -> {
                bookResponseDtos.add(BookResponseDto.builder().id(b.getId()).title(b.getTitle()).isbn(b.getIsbn()).publishedDate(b.getPublishedDate()).author(author).build());
            });
        }
        return bookResponseDtos;
    }

}
