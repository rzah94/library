package com.example.libproject.service;

import com.example.libproject.domain.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book getBook(long id);
    Book updateBook(Book book);
    Book createBook(Book book);
    void deleteBook(long id);
    List<Book> findByPersonId(long id);
}
