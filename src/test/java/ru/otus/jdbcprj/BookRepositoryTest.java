package ru.otus.jdbcprj;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.jdbcprj.model.Author;
import ru.otus.jdbcprj.model.Book;
import ru.otus.jdbcprj.model.Genre;
import ru.otus.jdbcprj.repository.AuthorRepository;
import ru.otus.jdbcprj.repository.BookRepository;
import ru.otus.jdbcprj.repository.GenreRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class BookRepositoryTest {

    private static final int EXPECTED_NUMBER_OF_BOOKS = 3;
    private static final String GENRE_ID = "3";
    private static final String AUTHOR_ID = "5";
    private static final String BOOK_ID = "3";
    private static final String BOOK_NAME = "New book";

    @Autowired
    BookRepository bookRepo;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    GenreRepository genreRepository;

    @Autowired
    MongoOperations mongoOperations;

    @DisplayName("Should return 3 records")
    @Test
    public void test_getAll() {
        List<Book> books = bookRepo.findAll();
        assertEquals(EXPECTED_NUMBER_OF_BOOKS, books.size());
    }

    @DisplayName("Check that a new record saved")
    @Test
    public void test_insert() {
        Author author = authorRepository.findById(AUTHOR_ID).get();
        Genre genre = genreRepository.findById(GENRE_ID).get();
        val book = new Book("0L", "Book name", author, genre);
        bookRepo.save(book);
        assertThat(book.getId()).isGreaterThan("0");
        mongoOperations.remove(book);
    }

    @DisplayName("Update name by id")
    @Test
    public void test_update() {
        val book = mongoOperations.findById(BOOK_ID, Book.class);
        String oldName = book.getName();

        val bookToModify = bookRepo.findById(BOOK_ID).get();
        bookToModify.setName(BOOK_NAME);
        bookRepo.save(bookToModify);
        val updatedBook = mongoOperations.findById(BOOK_ID, Book.class);

        assertThat(updatedBook.getName()).isNotEqualTo(oldName).isEqualTo(BOOK_NAME);
    }

    @DisplayName("Delete by id")
    @Test
    public void test_delete() {
        val book = mongoOperations.findById(BOOK_ID, Book.class);
        assertThat(book).isNotNull();

        bookRepo.deleteById(BOOK_ID);
        val deletedBook = mongoOperations.findById(BOOK_ID, Book.class);
        assertThat(deletedBook).isNull();
        mongoOperations.save(book);
    }
}
