package ru.otus.jdbcprj;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.jdbcprj.model.Genre;
import ru.otus.jdbcprj.repository.GenreRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class GenreRepositoryTest {

    private static final int EXPECTED_NUMBER_OF_GENRES = 9;
    private static final String GENRE_ID = "7";

    @Autowired
    GenreRepository repo;

    @DisplayName("Should return 9 records")
    @Test
    public void test_getAll() {
        List<Genre> genre = repo.findAll();
        assertEquals(EXPECTED_NUMBER_OF_GENRES, genre.size());
    }

    @DisplayName("Find by id")
    @Test
    public void test_getById() {
        Genre genre = repo.findById(GENRE_ID).get();
        assertThat(genre).isNotNull();
        assertEquals(GENRE_ID, genre.getId());
    }
}
