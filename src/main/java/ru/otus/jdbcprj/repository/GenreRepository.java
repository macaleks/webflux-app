package ru.otus.jdbcprj.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.jdbcprj.model.Genre;

@Repository
public interface GenreRepository extends MongoRepository<Genre, String> {
}
