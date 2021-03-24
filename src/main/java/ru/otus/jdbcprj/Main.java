package ru.otus.jdbcprj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.jdbcprj.model.Author;
import ru.otus.jdbcprj.model.Book;
import ru.otus.jdbcprj.model.Comment;
import ru.otus.jdbcprj.model.Genre;
import ru.otus.jdbcprj.repository.AuthorRepository;
import ru.otus.jdbcprj.repository.BookRepository;
import ru.otus.jdbcprj.repository.CommentRepository;
import ru.otus.jdbcprj.repository.GenreRepository;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class Main {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    GenreRepository genreRepository;
    @Autowired
    CommentRepository commentRepository;

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }

    @PostConstruct
    public void init() {
        List<Author> authors = Arrays.asList(new Author("1", "Neil", "Gaiman"),
                new Author("2", "Terry", "Pratchett"),
                new Author("3", "Lisa", "Lutz"),
                new Author("4", "David", "Hayward"),
                new Author("5", "Jodi", "Picoult"),
                new Author("6", "Samantha", "Leer")
        );
        authors.forEach(authorRepository::save);

        List<Genre> genres = Arrays.asList(
                new Genre("1", "biography"),
                new Genre("2", "fable"),
                new Genre("3", "realistic"),
                new Genre("4", "fiction"),
                new Genre("5", "poetry"),
                new Genre("6", "science fiction"),
                new Genre("7", "drama"),
                new Genre("8", "fantasy"),
                new Genre("9", "mystery")
        );
        genres.forEach(genreRepository::save);

        List<Book> books = Arrays.asList(
                new Book("1", "Good Omens",
                        authorRepository.findById("1").get(),
                        genreRepository.findById("7").get()),
                new Book("2", "Heads You Lose",
                        authorRepository.findById("4").get(),
                        genreRepository.findById("9").get()),
                new Book("3", "Between the Lines",
                        authorRepository.findById("6").get(),
                        genreRepository.findById("1").get())
        );
        books.forEach(bookRepository::save);

        List<Comment> comments = Arrays.asList(
                new Comment("1", bookRepository.findById("1").get(), "Good"),
                new Comment("2", bookRepository.findById("2").get(), "Nothing special"),
                new Comment("3", bookRepository.findById("3").get(), "Would recommend ")

        );
        comments.forEach(commentRepository::save);
    }
}
