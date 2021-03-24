package ru.otus.jdbcprj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.jdbcprj.repository.CommentRepository;
import ru.otus.jdbcprj.model.Book;
import ru.otus.jdbcprj.model.Comment;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repo;

    @Autowired
    public CommentServiceImpl(CommentRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Comment> findByBook(Book book) {
        return repo.findByBook(book);
    }

    @Override
    public Comment save(Comment comment) {
        return repo.save(comment);
    }
}
