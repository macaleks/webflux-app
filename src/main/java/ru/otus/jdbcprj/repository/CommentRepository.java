package ru.otus.jdbcprj.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.jdbcprj.model.Book;
import ru.otus.jdbcprj.model.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {

    List<Comment> findByBook(Book book);
}
