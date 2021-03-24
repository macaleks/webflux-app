package ru.otus.jdbcprj.service;

import ru.otus.jdbcprj.model.Book;

import java.util.List;

public interface BookService {

    List<Book> getAll();

    Book save(Book book);

    void deleteById(String id);

    void updateNameById(Book book);

    Book getById(String id);
}
