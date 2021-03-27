package ru.otus.jdbcprj.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import ru.otus.jdbcprj.model.Book;

@Repository
public interface BookRepository extends ReactiveMongoRepository<Book, String> {

    Mono<Book> getBookById(String id);

    Mono<Book> deleteBookById(String id);
}
