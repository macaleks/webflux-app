package ru.otus.jdbcprj.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ru.otus.jdbcprj.model.Author;
import ru.otus.jdbcprj.model.Book;
import ru.otus.jdbcprj.model.Genre;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository repository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    public void test_save() {
        Author author = authorRepository.save(new Author("Albert", "Kant")).block();
        Genre genre = genreRepository.save(new Genre("Poem")).block();
        Mono<Book> bookMono = repository.save(new Book("Stranger", author, genre));

        StepVerifier
                .create(bookMono)
                .assertNext(book -> {
                    assertNotNull(book.getId());
                    assertNotNull(book.getAuthor());
                    assertNotNull(book.getGenre());
                })
                .expectComplete()
                .verify();
    }

    @Test
    public void test_getBookById() {
        String expectedName = "New Stroy";

        Book book = repository.save(new Book(expectedName, null, null)).block();

        Mono<Book> bookById = repository.getBookById(book.getId());

        StepVerifier
                .create(bookById)
                .assertNext(b -> assertEquals(expectedName, b.getName()))
                .expectComplete()
                .verify();
    }

    @Test
    public void test_deleteBookById() {
        Book book = repository.save(new Book("Any name", null, null)).block();

        repository.deleteBookById(book.getId()).block();

        Book deletedBook = repository.getBookById(book.getId()).block();

        assertNull(deletedBook);
    }
}
