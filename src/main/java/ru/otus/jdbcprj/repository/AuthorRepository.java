package ru.otus.jdbcprj.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.jdbcprj.model.Author;

@Repository
public interface AuthorRepository extends MongoRepository<Author, String> {
}
