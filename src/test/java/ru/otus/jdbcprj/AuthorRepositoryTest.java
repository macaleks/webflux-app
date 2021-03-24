package ru.otus.jdbcprj;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.jdbcprj.model.Author;
import ru.otus.jdbcprj.repository.AuthorRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class AuthorRepositoryTest {

    private static final int EXPECTED_NUMBER_OF_AUTHORS = 6;
    private static final String AUTHOR_ID = "5";

    @Autowired
    AuthorRepository repo;

    @DisplayName("Should return 6 records")
    @Test
    public void test_getAll() {

        List<Author> authors = repo.findAll();
        assertEquals(EXPECTED_NUMBER_OF_AUTHORS, authors.size());
    }

    @DisplayName("Find by id")
    @Test
    public void test_getById() {
        Author author = repo.findById(AUTHOR_ID).get();
        assertThat(author).isNotNull();
        assertEquals(AUTHOR_ID, author.getId());
    }
}
