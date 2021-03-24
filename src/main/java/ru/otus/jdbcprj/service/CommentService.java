package ru.otus.jdbcprj.service;

import ru.otus.jdbcprj.model.Book;
import ru.otus.jdbcprj.model.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> findByBook(Book book);

    Comment save(Comment comment);
}
