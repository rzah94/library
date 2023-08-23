package com.example.libproject.repository;

import com.example.libproject.domain.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {


    List<Book> findAllByPerson_Id(long id);
}
