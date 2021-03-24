package ru.otus.jdbcprj.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.jdbcprj.model.Book;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
}
