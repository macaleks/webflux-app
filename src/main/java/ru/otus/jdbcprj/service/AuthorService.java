package ru.otus.jdbcprj.service;

import ru.otus.jdbcprj.model.Author;

import java.util.List;

public interface AuthorService {

    List<Author> getAll();

    Author getById(String id);
}
