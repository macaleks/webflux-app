package ru.otus.jdbcprj.service;

import ru.otus.jdbcprj.model.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> getAll();

    Genre getById(String id);
}
