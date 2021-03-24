package ru.otus.jdbcprj;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.jdbcprj.model.Book;
import ru.otus.jdbcprj.model.Comment;
import ru.otus.jdbcprj.repository.BookRepository;
import ru.otus.jdbcprj.repository.CommentRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class CommentRepositoryTest {

    private static final int EXPECTED_NUMBER_OF_COMMENTS = 1;
    private static final String BOOK_ID = "2";
    private static final String COMMENT = "Nice to have";

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    BookRepository bookRepository;

    @DisplayName("Find comments by book")
    @Test
    public void test_findComments() {
        Book book = bookRepository.findById(BOOK_ID).get();
        List<Comment> comments = commentRepository.findByBook(book);
        assertEquals(EXPECTED_NUMBER_OF_COMMENTS, comments.size());
    }

    @DisplayName("Add a new comment for a book")
    @Test
    public void test_insert() {
        Book book = bookRepository.findById(BOOK_ID).get();
        Comment comment = new Comment("0L", book, COMMENT);
        Comment savedComment = commentRepository.save(comment);

        assertThat(savedComment).isNotNull();
        assertThat(savedComment.getId()).isGreaterThan("0");
    }
}
